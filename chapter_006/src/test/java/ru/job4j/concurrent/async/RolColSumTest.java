package ru.job4j.concurrent.async;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RolColSumTest {

    private static int[][] matrix = new int[3][3];

    /**
     * 1  2  3
     * 4  5  6
     * 7  8  9
     */
    @Before
    public void initMatrix() {
        int counter = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = counter++;
            }
        }
    }

    @Test
    public void computeSync() {
        Sums[] sum = RolColSum.sum(matrix);

        assertEquals(12, sum[0].getColSum());
        assertEquals(15, sum[1].getColSum());
        assertEquals(18, sum[2].getColSum());

        assertEquals(6, sum[0].getRowSum());
        assertEquals(15, sum[1].getRowSum());
        assertEquals(24, sum[2].getRowSum());
    }

    @Test
    public void computeASync() {
        Sums[] sum = RolColSum.asyncSum(matrix);

        assertEquals(12, sum[0].getColSum());
        assertEquals(15, sum[1].getColSum());
        assertEquals(18, sum[2].getColSum());

        assertEquals(6, sum[0].getRowSum());
        assertEquals(15, sum[1].getRowSum());
        assertEquals(24, sum[2].getRowSum());
    }
}