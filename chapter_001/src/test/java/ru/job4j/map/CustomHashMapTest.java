package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class CustomHashMapTest {

    @Test
    public void whenPut2DifferentObjectsToMapThenGet() {
        CustomHashMap<User, String> map = new CustomHashMap<>();
        User user1 = new User("Jack", 2, new GregorianCalendar(1990, Calendar.JUNE, 15));
        User user2 = new User("Tom", 2, new GregorianCalendar(1990, Calendar.JUNE, 15));

        map.insert(user1, "first");
        map.insert(user2, "second");

        assertThat(map.get(user1), is("first"));
        assertThat(map.get(user2), is("second"));
    }

    @Test
    public void whenPut2SameObjectsToMapThenCallSize() {
        CustomHashMap<User, String> map = new CustomHashMap<>();
        User user1 = new User("Jack", 2, new GregorianCalendar(1990, Calendar.JUNE, 15));
        User user2 = new User("Jack", 2, new GregorianCalendar(1990, Calendar.JUNE, 15));

        map.insert(user1, "first");
        map.insert(user2, "second");

        assertThat(map.size(), is(1));
    }

    @Test
    public void whenPutThenDelete() {
        CustomHashMap<User, String> map = new CustomHashMap<>();
        User user = new User("Jack", 2, new GregorianCalendar(1990, Calendar.JUNE, 15));

        map.insert(user, "first");
        map.delete(user);
        assertThat(map.get(user), is(nullValue()));
    }
}