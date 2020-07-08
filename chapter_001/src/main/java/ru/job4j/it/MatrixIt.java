package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data[row].length - (column + 1) >= 0) {
            return true;
        } else {
            for (int i = row + 1; i < data.length - row; i++) {
                    if (data[i].length - row >= 0) {
                        return true;
                    }
            } return false;
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (column > data[row].length - 1) {
            while (row < data.length) {
                row++;
                column = 0;
                if (data[row].length - column > 0) {
                    return data[row][column++];
                }
            }
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

}