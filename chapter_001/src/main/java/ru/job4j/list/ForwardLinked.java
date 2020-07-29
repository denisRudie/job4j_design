package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int counter = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            counter++;
            return;
        }
        Node<T> tail = getLastNode();
        tail.next = node;
        counter++;
    }

    public void revert() {
        if (head != null) {
            Node<T> prev = null;
            Node<T> current = head;
            Node<T> next = head.next;

            while (next != null) {
                current.next = prev;
                prev = current;
                current = next;
                next = current.next;
            }
            head = current;
            head.next = prev;
        }
    }

    public T deleteFirst() {
        T value;
        if (head == null) {
            throw new NoSuchElementException();
        }
        value = head.value;
        head = head.next;
        counter--;
        return value;
    }

    public T deleteLast() {
        T value;
        if (counter == 0) {
            throw new NoSuchElementException();
        }

        if (counter == 1) {
            value = head.value;
            head = null;
        } else {
            value = getLastNode().value;
            Node<T> prev = head;
            for (int i = 0; i < counter - 1; i++) {
                prev = prev.next;
            }
            prev.next = null;
        }
        counter--;
        return value;
    }

    public Node<T> getLastNode() {
        Node<T> last;
        if (head == null) {
            throw new NoSuchElementException();
        }
        last = head;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}