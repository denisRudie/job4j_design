package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Author;
import ru.job4j.model.Book;

public class HbmRunBook {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Author author1 = new Author();
            Author author2 = new Author();
            author1.setName("author1");
            author2.setName("author2");
            for (int i = 1; i <= 3; i++) {
                Book book = new Book();
                book.setName("Book" + i);
                author1.addBook(book);
            }

            Book book = new Book();
            book.setName("Book4");
            author1.addBook(book);
            author2.addBook(book);
            session.persist(author1);
            session.persist(author2);

            session.delete(session.get(Author.class, 5));
            session.getTransaction().commit();
        }
    }
}
