package ru.job4j.map;

import java.util.LinkedList;
import java.util.List;

public class CustomHashMap<K, V> {

    private int DEFAULT_SIZE = 16;
    private Node<K, V>[] hashTable;
    private float threshold;
    private int size = 0;

    public CustomHashMap() {
        hashTable = new Node[DEFAULT_SIZE];
        threshold = hashTable.length * 0.75f;
    }

    public boolean insert(K key, V value) {
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
        hashTable = new Node[hashTable.length * 2];

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
        return key.hashCode() % hashTable.length;
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

    private static class Node<K, V> {

        K key;
        V value;
        List<Node<K, V>> nodeList = new LinkedList<>();

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
