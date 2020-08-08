package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        Map<Character, Integer> leftMap = symbolsToMap(left);
        Map<Character, Integer> rightMap = symbolsToMap(right);

        return leftMap.equals(rightMap);
    }

    private static Map<Character, Integer> symbolsToMap(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        return map;
    }

}