package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LinkedArrayTest {

    @Test
    public void getValueByIndex() {
        LinkedArray<Integer> linkedArray = new LinkedArray<>();
        linkedArray.add(1);
        linkedArray.add(2);
        linkedArray.add(3);
        assertThat(linkedArray.get(2), is(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        LinkedArray<Integer> linkedArray = new LinkedArray<>();
        linkedArray.get(2);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenItHaventEnoughElements() {
        LinkedArray<Integer> linkedArray = new LinkedArray<>();
        Iterator<Integer> it = linkedArray.iterator();
        it.next();
    }

    @Test
    public void whenCallItNext() {
        LinkedArray<Integer> linkedArray = new LinkedArray<>();
        linkedArray.add(1);
        linkedArray.add(2);
        Iterator<Integer> it = linkedArray.iterator();
        int first = it.next();
        int second = it.next();
        assertThat(first, is(1));
        assertThat(second, is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        LinkedArray<String> linkedArray = new LinkedArray<>();
        linkedArray.add("first");
        Iterator<String> it = linkedArray.iterator();
        linkedArray.add("second");
        it.next();
    }
}