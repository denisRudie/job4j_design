package ru.job4j.tree;

import java.util.*;
import java.util.Optional;

public class Tree<E> implements SimpleTree<E> {

    private final Node<E> root;
    private int size;
    private boolean binary;

    Tree(final E root) {
        this.root = new Node<>(root);
        this.size = 1;
        this.binary = true;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentNode = findBy(parent);

        if (parentNode.isEmpty()) {
            return false;
        }

        Optional<Node<E>> childNode = findBy(child);

        if (childNode.isEmpty()) {
            parentNode.get().children.add(new Node<>(child));
            rsl = true;
        }

        if (parentNode.get().children.size() > 2) {
            binary = false;
        }

        size++;
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        return  binary && size > 1;
    }
}
