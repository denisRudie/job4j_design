package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void testAddMultipleDigits() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(2);
        set.add(2);
        set.add(2);
        set.add(1);

        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void testAddMultipleStrings() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("2");
        set.add("2");

        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("1"));
        assertThat(it.next(), is("2"));
    }

    @Test (expected = NoSuchElementException.class)
    public void callItWithVoidList() {
        SimpleSet<String> set = new SimpleSet<>();
        Iterator<String> it = set.iterator();
        it.next();
    }

    @Test
    public void addNullAndCallIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add(null);
        set.add("1");

        Iterator<String> it = set.iterator();
        assertThat(it.next(), is(nullValue()));
        assertThat(it.next(), is("1"));
    }
}