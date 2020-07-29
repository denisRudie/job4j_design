package ru.job4j.list;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinkedTest {

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddOneThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenHaveVoidListThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        Iterator<Integer> it = linked.iterator();
        it.next();
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}
