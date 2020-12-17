package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Candidate;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmRun {
    private final static HbmRun INST = new HbmRun();
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmRun() {

    }

    public static HbmRun instOf() {
        return INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        T rsl;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = command.apply(session);
            session.getTransaction().commit();
        }
        return rsl;
    }

    private void consume(final Consumer<Session> command) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            command.accept(session);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        HbmRun store = HbmRun.instOf();

        Candidate cand1 = Candidate.of("Mike", 10, 10_000.00f);
        Candidate cand2 = Candidate.of("Tom", 2, 6_000.00f);
        Candidate cand3 = Candidate.of("Jack", 25, 13_000.00f);

//save candidates
//        store.consume(session -> session.save(cand1));
//        store.consume(session -> session.save(cand2));
//        store.consume(session -> session.save(cand3));
//get all candidates
        List<Candidate> candidates = store.tx(
                session -> session.createQuery("from Candidate ").list()
        );
        System.out.println("all candidates: " + candidates);
//get candidate by id
        Candidate candById = store.tx(session -> session.get(Candidate.class, 1));
        System.out.println("candidate by id: " + candById);
//get candidates by name
        List<Candidate> candidatesByName = store.tx(
                session -> session.createQuery("from Candidate where name = :name")
                        .setParameter("name", "Tom")
                        .list()
        );
        for (Candidate candidate : candidatesByName) {
            System.out.println("candidate by name: " + candidate);
        }
//update candidate
        store.consume(session -> session.createQuery(
                "update Candidate set name = :name where id =:id")
                .setParameter("name", "John")
                .setParameter("id", 1)
                .executeUpdate()
        );
        Candidate updateCand = store.tx(session -> session.get(Candidate.class, 1));
        System.out.println("Updated cand: " + updateCand);
//delete candidate by id
        store.consume(session -> session.createQuery(
                "delete from Candidate where id = :id")
                .setParameter("id", 3)
                .executeUpdate()
        );
        List<Candidate> candidatesAfterDelete = store.tx(
                session -> session.createQuery("from Candidate ").list()
        );
        System.out.println("Candidates after delete: " + candidatesAfterDelete.size());
    }
}