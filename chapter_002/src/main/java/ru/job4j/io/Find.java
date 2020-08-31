package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Поиск файла
 *
 * java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 * -d - директория в которой начинать поиск.
 * -n - имя файл, маска, либо регулярное выражение.
 * -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
 * -o - результат записать в файл.
 */
public class Find {
    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);

        Path src = getSrc(argsList);
        Path trg = getTrg(argsList);

        searchByCriteria(argsList, src, trg);
    }

    /**
     * @param args Лист параметров.
     * @return Путь, в котором следует начать поиск.
     */
    private static Path getSrc(List<String> args) {
        if (args.contains(Type.SRC.getCmd())) {
            String srcPath = args.get(
                    args.indexOf(Type.SRC.getCmd()) + 1);
            if (!Type.SRC.match(srcPath)) {
                throw new IllegalArgumentException("Incorrect source folder format.");
            }
            return Path.of(srcPath);
        } else {
            throw new IllegalArgumentException("Parameter -d has not been found.");
        }
    }

    /**
     * @param args Лист параметров.
     * @return Путь к файлу, в который следует записать результат поиска.
     */
    private static Path getTrg(List<String> args) {
        if (args.contains(Type.TRG.getCmd())) {
            String trgPath = args.get(
                    args.indexOf(Type.TRG.getCmd()) + 1);
            if (!Type.TRG.match(trgPath)) {
                throw new IllegalArgumentException("Incorrect target file format.");
            }
            return Path.of(trgPath);
        } else {
            throw new IllegalArgumentException("Parameter -o has not been found.");
        }
    }

    /**
     * Поиск файла по критерию.
     * Определение типа критерия.
     * Выполнение поиска в зависимости от типа критерия.
     *
     * @param args Лист параметров.
     * @param src Путь, в котором следует начать поиск.
     * @param trg Путь к файлу, в который следует записать результат поиска.
     */
    private static void searchByCriteria(List<String> args, Path src, Path trg) {
        Type searchCriteria;
        if (args.contains(Type.MASK.getCmd())) {
            searchCriteria = Type.MASK;
        } else if (args.contains(Type.NAME.getCmd())) {
            searchCriteria = Type.NAME;
        } else if (args.contains(Type.REGEX.getCmd())) {
            searchCriteria = Type.REGEX;
        } else {
            throw new IllegalArgumentException("Criteria type has not been found."
                    + "Please choose one of the next criterias:" + System.lineSeparator()
                    + "-m - search by mask" + System.lineSeparator()
                    + "-f - search by file full name" + System.lineSeparator()
                    + "-r - search by regular expression");
        }

        String searchRequest = args.get(
                args.indexOf("-n") + 1);

        switch (searchCriteria) {
            case MASK:
                if (!Type.MASK.match(searchRequest)) {
                    throw new IllegalArgumentException("Incorrect mask format.");
                }
                search(src, trg,
                        p -> p.toFile()
                                .getName()
                                .endsWith(searchRequest.substring(1)));
                break;
            case NAME:
                if (!Type.NAME.match(searchRequest)) {
                    throw new IllegalArgumentException("Incorrect file name format.");
                }
                search(src, trg,
                        p -> p.toFile()
                                .getName()
                                .equals(searchRequest));
                break;
            case REGEX:
                if (!Type.REGEX.match(searchRequest)) {
                    throw new IllegalArgumentException("Incorrect regex format.");
                }
                search(src, trg,
                        p -> p.toFile()
                                .getName()
                                .matches(searchRequest));
                break;
            default:
                break;
        }
    }

    /**
     * Выполняет поиск файлов, удовлетворяющих предикату и записывает их в файл.
     */
    private static void search(Path src, Path trg, Predicate<Path> criteria) {
        SearchFiles searcher = new SearchFiles(criteria);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(trg.toString()))) {
            Files.walkFileTree(src, searcher);
            String result = searcher.getPathList().stream()
                    .map(Path::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FileVisitor
     */
    private static class SearchFiles extends SimpleFileVisitor<Path> {
        private final Predicate<Path> criteria;
        private final List<Path> pathList = new ArrayList<>();

        private SearchFiles(Predicate<Path> criteria) {
            this.criteria = criteria;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (criteria.test(file)) {
                pathList.add(file);
            }
            return CONTINUE;
        }

        private List<Path> getPathList() {
            return pathList;
        }
    }

    /**
     * Enum для типов критериев поиска: маска, полное имя, регулярное выражение.
     */
    private enum Type {
        MASK("-m") {
            @Override
            public boolean match(String s) {
                return s.matches("^\\*\\.\\w+$");
            }
        },
        NAME("-f") {
            @Override
            public boolean match(String s) {
                return s.matches("^\\w+\\.\\w+$");
            }
        },
        REGEX("-r") {
            @Override
            public boolean match(String s) {
                return s.matches(".+");
            }
        },
        SRC("-d") {
            @Override
            public boolean match(String s) {
                return s.matches("^\\p{L}:/\\S*");
            }
        },
        TRG("-o") {
            @Override
            public boolean match(String s) {
                return s.matches("(^\\w:/\\S*\\.\\w+$)|(\\w+\\.\\w+)");
            }
        };

        private final String cmd;

        Type(String cmd) {
            this.cmd = cmd;
        }

        public abstract boolean match(String s);

        private String getCmd() {
            return cmd;
        }
    }
}