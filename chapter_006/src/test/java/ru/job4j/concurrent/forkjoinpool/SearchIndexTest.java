package ru.job4j.concurrent.forkjoinpool;

import org.junit.Test;
import ru.job4j.concurrent.User;

import java.util.Optional;

import static org.junit.Assert.*;

public class SearchIndexTest {

    @Test
    public void whenGenerateUsersAndTryToFindById() {
        User[] users = UsersGenerator.generate(1_000_000);
        SearchIndex search = new SearchIndex(users);
        assertEquals("user100000", search.searchById(100_000).get().getName());
    }

    @Test
    public void whenGenerateUsersAndTryToFindNotExistingUser() {
        User[] users = UsersGenerator.generate(1_000_000);
        SearchIndex search = new SearchIndex(users);
        assertEquals(Optional.empty(), search.searchById(100_000_000));
    }
}