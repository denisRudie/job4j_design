package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        for (Path src : sources) {
            packSingleFile(src.toFile(), target.toFile());
            System.out.println("writing");
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getAbsolutePath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgZip argZip = new ArgZip(args);

        if (!argZip.valid()) {
            throw new IllegalArgumentException("Incorrect arguments");
        }

        try {
            List<Path> pathList = Search.search(Path.of(argZip.directory()),
                    p -> !p.toFile().getName().endsWith(argZip.exclude()));

            new Zip().packFiles(pathList, Path.of(argZip.output()));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        new Zip().packSingleFile(
//                new File("./chapter_005/pom.xml"),
//                new File("./chapter_005/pom.zip")
//        );
    }
}
