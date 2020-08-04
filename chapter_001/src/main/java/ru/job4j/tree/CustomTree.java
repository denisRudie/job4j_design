package ru.job4j.tree;

import java.util.Objects;

public class CustomTree<E> {

    private Node<E> root;
    private int size = 0;

    public CustomTree(E root) {
        this.root = new Node<>(root);
    }

    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);
        Node<E> lastNode = getLastNode(newNode, root);

        if (lastNode.compareTo(newNode) > 0) {
            lastNode.right = newNode;
            size++;
            return true;
        }

        if (lastNode.compareTo(newNode) < 0) {
            lastNode.left = newNode;
            size++;
            return true;
        }

        return false;
    }

    private Node<E> getLastNode(Node<E> newNode, Node<E> oldNode) {
        Node<E> lastNode = oldNode;

        int compare = lastNode.compareTo(newNode);

        if (compare > 0 && lastNode.right != null) {
            lastNode = getLastNode(newNode, oldNode.right);
            return lastNode;
        }

        if (compare < 0 && lastNode.left != null) {
            lastNode = getLastNode(newNode, oldNode.left);
            return lastNode;
        }

        return lastNode;
    }

    private static class Node<E> implements Comparable<E> {
        private Node<E> left;
        private Node<E> right;
        private E value;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        @Override
        public int compareTo(Object obj) {
            Node<E> node = (Node<E>) obj;
            return node.hashCode() - this.hashCode();
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
