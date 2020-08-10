package ru.job4j.analizator;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenPut2SameLists() {
        Analize analizator = new Analize();

        List<Analize.User> prev = List.of(new Analize.User(1, "Mike"),
                new Analize.User(2, "John"));

        List<Analize.User> curr = List.of(new Analize.User(1, "Mike"),
                new Analize.User(2, "John"));

        Analize.Info info = analizator.diff(prev, curr);

        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenPut2DiffLists() {
        Analize analizator = new Analize();

        List<Analize.User> prev = List.of(new Analize.User(1, "Mike"),
                new Analize.User(2, "John"));

        List<Analize.User> curr = List.of(new Analize.User(1, "Tom"),
                new Analize.User(3, "Tiffany"));

        Analize.Info info = analizator.diff(prev, curr);

        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }
}