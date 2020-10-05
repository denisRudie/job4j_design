package ru.job4j.kiss;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    @Test
    public void getMaxInt() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1, 2, 3, 14, 0, -89);
        int maxValue = maxMin.max(list, Integer::compareTo);
        assertThat(maxValue, is(14));
    }

    @Test
    public void getMinInt() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1, 2, 3, 14, 0, -89);
        int minValue = maxMin.min(list, Integer::compareTo);
        assertThat(minValue, is(-89));
    }
}