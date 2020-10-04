package ru.job4j.post;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PostTest {

    @Test
    public void whenAddSimilarEmailsUsersThenUnion() {
        Post post = new Post();

        User user1 = new User("Mike");
        user1.addEmail("123@mail.ru");
        user1.addEmail("321@yandex.ru");
        user1.addEmail("test.ru");
        post.addUser(user1);

        User user2 = new User("Tom");
        user2.addEmail("test.com");
        post.addUser(user2);

        User user3 = new User("John");
        user3.addEmail("666@gmail.ru");
        user3.addEmail("000@yandex.ru");
        user3.addEmail("test.ru");
        user3.addEmail("test.com");
        post.addUser(user3);

        List<User> list = post.union();
        assertThat(list.size(), is(1));
    }

    @Test
    public void whenAddDifferentEmailsUsersThenUnion() {
        Post post = new Post();

        User user1 = new User("Mike");
        user1.addEmail("123@mail.ru");
        post.addUser(user1);

        User user2 = new User("Tom");
        user2.addEmail("test.com");
        post.addUser(user2);

        User user3 = new User("John");
        user3.addEmail("666@gmail.ru");
        post.addUser(user3);

        List<User> list = post.union();
        assertThat(list.size(), is(3));
    }

    @Test
    public void whenAddSimilarEmailsUsersThenBubbleUnion() {
        Post post = new Post();

        User user1 = new User("Mike");
        user1.addEmail("123@mail.ru");
        user1.addEmail("321@yandex.ru");
        user1.addEmail("test.ru");
        post.addUser(user1);

        User user2 = new User("Tom");
        user2.addEmail("test.com");
        post.addUser(user2);

        User user3 = new User("John");
        user3.addEmail("666@gmail.ru");
        user3.addEmail("000@yandex.ru");
        user3.addEmail("test.ru");
        user3.addEmail("test.com");
        post.addUser(user3);

        List<User> list = post.bubbleUnion();
        assertThat(list.size(), is(1));
    }

    @Test
    public void whenAddDifferentEmailsUsersThenBubbleUnion() {
        Post post = new Post();

        User user1 = new User("Mike");
        user1.addEmail("123@mail.ru");
        post.addUser(user1);

        User user2 = new User("Tom");
        user2.addEmail("test.com");
        post.addUser(user2);

        User user3 = new User("John");
        user3.addEmail("666@gmail.ru");
        post.addUser(user3);

        List<User> list = post.bubbleUnion();
        assertThat(list.size(), is(3));
    }
}