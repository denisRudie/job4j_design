package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;

public class SimpleArrayTest {

    @Test
    public void createListAndTestAddAndGet() {
        SimpleArray<Integer> testList = new SimpleArray<>(3);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        Assert.assertThat(testList.get(0), is(1));
        Assert.assertThat(testList.get(1), is(2));
        Assert.assertThat(testList.get(2), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromNull() {
        SimpleArray<Integer> testList = new SimpleArray<>(3);
        int test = testList.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCallNextFromEmpty() {
        SimpleArray<Integer> testList = new SimpleArray<>(3);
        Iterator<Integer> it = testList.iterator();
        it.next();
    }

    @Test
    public void whenSetNewValue() {
        SimpleArray<Integer> testList = new SimpleArray<>(1);
        testList.add(1);
        testList.set(0, 2);
        Assert.assertThat(testList.get(0), is(2));
    }

    @Test
    public void whenRemoveElementNextElementsIndexDecrements() {
        SimpleArray<Integer> testList = new SimpleArray<>(4);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.remove(1);
        Assert.assertThat(testList.get(0), is(1));
        Assert.assertThat(testList.get(1), is(3));
        Assert.assertThat(testList.get(2), is(4));
    }
}