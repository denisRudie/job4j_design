package ru.job4j.menu;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleMenuTest {
    private final MenuAdvanced simpleMenu = new SimpleMenu();

    @Before
    public void addTestingData() {
        SimpleMenuItem simpleMenuItem = new SimpleMenuItem("1");
        SimpleMenuItem simpleMenuItem2 = new SimpleMenuItem("2");
        SimpleMenuItem son1 = new SimpleMenuItem("1.1");
        SimpleMenuItem son11 = new SimpleMenuItem("1.1.1");
        SimpleMenuItem son2 = new SimpleMenuItem("1.2");
        SimpleMenuItem son21 = new SimpleMenuItem("1.2.1");
        SimpleMenuItem son22 = new SimpleMenuItem("1.2.2");
        simpleMenuItem.setChildren(son1);
        simpleMenuItem.setChildren(son2);
        son1.setChildren(son11);
        son2.setChildren(son21);
        son2.setChildren(son22);

        simpleMenu.add(simpleMenuItem);
        simpleMenu.add(simpleMenuItem2);
    }

    @Test
    public void whenSearchExistingMenuItem() {
        Optional<MenuItem> item = simpleMenu.findItemByPredicate(i -> i.getName().equals("1.2.2"));
        assertTrue(item.isPresent());
        assertThat(item.get().getName(), is("1.2.2"));
    }

    @Test
    public void menuToStringEqualsOutputFormat() {
        StringBuilder expected = new StringBuilder()
                .append("1").append(System.lineSeparator())
                .append("  1.1").append(System.lineSeparator())
                .append("    1.1.1").append(System.lineSeparator())
                .append("  1.2").append(System.lineSeparator())
                .append("    1.2.1").append(System.lineSeparator())
                .append("    1.2.2").append(System.lineSeparator())
                .append("2").append(System.lineSeparator());

        assertThat(simpleMenu.toString(), is(expected.toString()));
    }
}