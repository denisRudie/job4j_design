package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target, int pathNameCounter) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path src : sources) {
                ZipEntry ze = new ZipEntry(src.subpath(pathNameCounter,
                        src.getNameCount()).toString());
                zip.putNextEntry(ze);

                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(src.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));

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
            Path path = Path.of(argZip.directory());
            List<Path> pathList = Search.search(path,
                    p -> !p.toFile().getName().endsWith(argZip.exclude()));

            new Zip().packFiles(pathList, Path.of(argZip.output()), path.getNameCount());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        new Zip().packSingleFile(
//                new File("./chapter_005/pom.xml"),
//                new File("./chapter_005/pom.zip")
//        );
    }
}
