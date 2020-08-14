package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {
    private static List<Path> pathList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, "txt").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return pathList;
    }

    public static class SearchFiles extends SimpleFileVisitor<Path> {
        private Predicate<Path> pr;

        public SearchFiles(Predicate<Path> pr) {
            this.pr = pr;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (pr.test(file)) {
                pathList.add(file.toAbsolutePath());
            }
            return CONTINUE;
        }
    }
}