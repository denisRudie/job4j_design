package ru.job4j.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Cache implementation with SoftReferences.
 */
public class SoftRefCache {
    private static final Logger LOG = LoggerFactory.getLogger(SoftRefCache.class);
    private Map<String, SoftReference<String>> cache;
    private String folder;
    private List<Path> textFiles;

    private SoftRefCache(String folder) {
        this.cache = new HashMap<>();
        this.folder = folder;
    }

    /**
     * Method for reading .txt file.
     * Before reading file content, method check that Cache contains file.
     *
     * @param fileName file, which have to read.
     * @return text from file.
     */
    public String getText(String fileName) {
        String text = "";

        if (cache.containsKey(fileName) && cache.get(fileName) != null) {
            text = cache.get(fileName).get();
        } else {
            Optional<Path> opt = textFiles.stream()
                    .filter(p -> p.getFileName().toString().equals(fileName))
                    .findFirst();
            if (opt.isPresent()) {
                Path path = opt.get();
                try {
                    text = Files.readString(path);
                    cache.put(fileName, new SoftReference<>(text));
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            } else {
                throw new IllegalArgumentException("file doesn't exist in directory");
            }
        }
        return text;
    }

    /**
     * Fabric method for creating SoftRefCache objects.
     * Before creating object method validate directory.
     * After creating method adds all files from directory with .txt format to list.
     *
     * @return SoftRefCache object.
     */
    public static SoftRefCache createCache(String directory) {
        Path path = Paths.get(directory);

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Incorrect directory");
        }

        SoftRefCache softCache = new SoftRefCache(directory);

        try {
            softCache.textFiles = Files.list(path)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return softCache;
    }
}
