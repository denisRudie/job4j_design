package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;

public class Article {

    /**
     * Метод проверяет факт наличия всех слов из строки line в строке origin.
     * Также учитывается количество слов.
     *
     * @param origin исходная строка.
     * @param line строка для проверки.
     * @return признак наличия всех слов из строки line в origin.
     */
    public static boolean generateBy(String origin, String line) {

        boolean rsl = true;
        Map<String, Integer> map = new HashMap<>();
        String[] wordsOrigin = origin.replaceAll("\\p{P}", "").split("\\s");

        for (String word : wordsOrigin) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        String[] wordsLine = line.split("\\s");

        for (String s : wordsLine) {
            if (map.containsKey(s)) {
                if (map.get(s) > 1) {
                    map.put(s, map.get(s) - 1);
                } else {
                    map.remove(s);
                }
            } else {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
