package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Required arguments not found. "
                    + "Choose root folder and file extension in params");
        }

        Path start = Paths.get(args[0]);

        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("It is not directory: %s", start));
        }

        search(start, p -> p.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> pr) throws IOException {
        SearchFiles searcher = new SearchFiles(pr);
        Files.walkFileTree(root, searcher);
        return searcher.getPathList();
    }

    public static class SearchFiles extends SimpleFileVisitor<Path> {
        private Predicate<Path> pr;
        private List<Path> pathList = new ArrayList<>();

        public SearchFiles(Predicate<Path> pr) {
            this.pr = pr;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (pr.test(file)) {
                pathList.add(file);
            }
            return CONTINUE;
        }

        public List<Path> getPathList() {
            return pathList;
        }
    }
}