package ru.job4j.map;

import java.util.*;

public class CustomHashMap<K, V> implements Iterable<V> {

    private int DEFAULT_CAPACITY = 16;
    private int capacity;
    private Node<K, V>[] hashTable;
    private float threshold;
    private int size;
    private int modCount;

    public CustomHashMap() {
        capacity = DEFAULT_CAPACITY;
        hashTable = new Node[capacity];
        threshold = capacity * 0.75f;
        size = 0;
        modCount = 0;
    }

    public boolean insert(K key, V value) {
        modCount++;

        if (size + 1 >= threshold) {
            threshold *= 2;
            doubleCapacity();
        }

        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);

        if (hashTable[index] == null) {
            return simpleAdd(newNode, index);
        }

        for (Node<K, V> node : hashTable[index].nodeList) {
            if (node.key.hashCode() == key.hashCode()) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return true;
                }
            }
        }

        hashTable[index].nodeList.add(newNode);
        size++;
        return true;
    }

    private void doubleCapacity() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[capacity * 2];

        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> innerNode : node.nodeList) {
                    insert(innerNode.key, innerNode.value);
                }
            }
        }
    }

    private boolean simpleAdd(Node<K, V> newNode, int index) {
        hashTable[index] = new Node<>(null, null);
        hashTable[index].nodeList.add(newNode);
        size++;
        return true;
    }

    public int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    public V get(K key) {
        int index = hash(key);

        if (hashTable[index] != null) {
            for (Node<K, V> node : hashTable[index].nodeList) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean delete(K key) {
        int index = hash(key);

        if (hashTable[index] != null) {
            for (Node<K, V> node : hashTable[index].nodeList) {
                if (node.key.hashCode() == key.hashCode()) {
                    if (node.key.equals(key)) {
                        hashTable[index].nodeList.remove(node);
                        size--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int bucketCounter = 0;
            private int returnedValuesCounter = 0;
            private Iterator<Node<K, V>> innerIterator = null;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (returnedValuesCounter == size) {
                    return false;
                }

                if (innerIterator == null || !innerIterator.hasNext()) {
                    bucketCounter++;

                    while (bucketCounter < capacity - 1 && hashTable[bucketCounter] == null) {
                        bucketCounter++;
                    }

                    if (bucketCounter < capacity) {
                        innerIterator = hashTable[bucketCounter].nodeList.iterator();
                    } else {
                        return false;
                    }
                }
                return innerIterator.hasNext();
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                returnedValuesCounter++;
                return innerIterator.next().value;
            }
        };
    }

    private static class Node<K, V> {

        private K key;
        private V value;
        private List<Node<K, V>> nodeList = new LinkedList<>();

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
