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

//        Candidate cand1 = Candidate.of("Mike", 10, 10_000.00f);
//        Candidate cand2 = Candidate.of("Tom", 2, 6_000.00f);
//        Candidate cand3 = Candidate.of("Jack", 25, 13_000.00f);

//save candidates
//        store.consume(session -> session.save(cand1));
//        store.consume(session -> session.save(cand2));
//        store.consume(session -> session.save(cand3));
//create vacancies and add them to vacansy bank
//        Vacancy vac1 = Vacancy.of("driver");
//        Vacancy vac2 = Vacancy.of("pilot");
//        Vacancy vac3 = Vacancy.of("JAVA developer");
//
//        VacancyBank vacancyBank = VacancyBank.of("HH");
//        vacancyBank.addVacancy(vac1);
//        vacancyBank.addVacancy(vac2);
//        vacancyBank.addVacancy(vac3);
//
//        store.consume(session -> session.save(vacancyBank));

//        VacancyBank bank = store.tx(session -> session.get(VacancyBank.class, 1));
//        Candidate cand1fromDb = store.tx(session -> session.get(Candidate.class, 1));
//        cand1fromDb.setVacancyBank(bank);
//        store.consume(session -> session.update(cand1fromDb));

        List<Candidate> candidates = store.tx(session ->
                session.createQuery("select distinct c from Candidate c " +
                        "join fetch c.vacancyBank b " +
                        "join fetch b.vacancies")
                        .list()
        );
        System.out.println(candidates);
    }
}