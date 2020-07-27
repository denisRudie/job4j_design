package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedArray<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    public LinkedArray() {
    }

    public void add(E value) {
        Node<E> newNode = new Node<>(value, null, null);
        if (size == 0) {
            first = newNode;
        } else if (size == 1) {
            first.next = newNode;
            newNode.prev = last;
        } else {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> node = null;
            private int cursor = 0;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (cursor != 0) {
                    node = node.next;
                } else {
                    node = first;
                }
                cursor++;
                return node.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}