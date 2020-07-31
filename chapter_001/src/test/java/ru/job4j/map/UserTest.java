package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void whenAddSameUserInMapAndPrintMap() {
        Map<User, Object> userMap = new HashMap<>();

        userMap.put(new User("Jack", 2, new GregorianCalendar(1990, Calendar.JUNE, 15)), "first");
        userMap.put(new User("Jack", 2, new GregorianCalendar(1990, Calendar.JUNE, 15)), "second");

        System.out.println(userMap);
    }
}
