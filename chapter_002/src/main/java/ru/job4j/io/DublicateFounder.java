package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DublicateFounder {

    /**
     * Метод для поиска дубликатов. Дубликатами считаются файлы,
     * имеющие одинаковое название и размер.
     *
     * Алгоритм:
     * 1. Обойти дерево, найти файлы с одинаковыми названиями, сложить в мапу.
     * 2. Обойти мапу, среди файлов с одинаковыми названиями найти файлы
     * с одинаковыми размерами, сложить в мапу.
     *
     * @param folder Папка для поиска дубликатов.
     * @throws IOException Possible.
     */
    public static void foundDublicate(Path folder) throws IOException {
        MyVisitor visitor = new MyVisitor();
        Files.walkFileTree(folder, visitor);
        Map<Path, List<Path>> visitorMap = visitor.getMap();

        for (Map.Entry<Path, List<Path>> pathListEntry : visitorMap.entrySet()) {
            Path key = pathListEntry.getKey();
            List<Path> value = pathListEntry.getValue();
            if (value.size() > 1) {
                Map<Long, List<Path>> sameSize = new HashMap<>();

                for (Path v : value) {
                    long size = v.toFile().length();

                    if (!sameSize.containsKey(size)) {
                        sameSize.put(size, new ArrayList<>());
                    }
                    sameSize.get(size).add(v.toAbsolutePath());
                }

                for (Map.Entry<Long, List<Path>> longListEntry : sameSize.entrySet()) {
                    if (longListEntry.getValue().size() > 1) {
                        System.out.println(key + " size: " + longListEntry.getKey());
                        for (Path path : longListEntry.getValue()) {
                            System.out.println(path.toAbsolutePath());
                        }
                    }
                }
            }
        }
    }

    public static class MyVisitor extends SimpleFileVisitor<Path> {
        private Map<Path, List<Path>> map = new HashMap<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (!map.containsKey(file.getFileName())) {
                map.put(file.getFileName(), new ArrayList<>());
            }
            map.get(file.getFileName()).add(file.toAbsolutePath());
            return FileVisitResult.CONTINUE;
        }

        public Map<Path, List<Path>> getMap() {
            return map;
        }
    }

    public static void main(String[] args) throws IOException {
        foundDublicate(Paths.get("."));
    }
}
