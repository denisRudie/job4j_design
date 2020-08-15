package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

public class DublicateFounder {

    /**
     * Метод для поиска дубликатов. Дубликатами считаются файлы,
     * имеющие одинаковое название и размер.
     *
     * @param folder Папка для поиска дубликатов.
     * @throws IOException Possible.
     */
    public static void foundDublicate(Path folder) throws IOException {
        MyVisitor visitor = new MyVisitor();
        Files.walkFileTree(folder, visitor);
        visitor.getMap().entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> Stream.of(e.getValue()))
                .forEach(System.out::println);
    }

    public static class MyVisitor extends SimpleFileVisitor<Path> {
        private Map<Node<Path, Long>, List<Path>> map = new HashMap<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Node<Path, Long> newNode = new Node<>(file.getFileName(), file.toFile().length());

            if (!map.containsKey(newNode)) {
                map.put(newNode, new ArrayList<>());
            }
            map.get(newNode).add(file.toAbsolutePath());

            return FileVisitResult.CONTINUE;
        }

        public Map<Node<Path, Long>, List<Path>> getMap() {
            return map;
        }

        private static class Node<K, V> {
            K path;
            V size;

            public Node(K path, V size) {
                this.path = path;
                this.size = size;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node<?, ?> node = (Node<?, ?>) o;
                return Objects.equals(path, node.path) &&
                        Objects.equals(size, node.size);
            }

            @Override
            public int hashCode() {
                return Objects.hash(path, size);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        foundDublicate(Paths.get("."));
    }
}
