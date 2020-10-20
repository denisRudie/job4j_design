package ru.job4j.tictactoe;

import ru.job4j.tictactoe.interfaces.Desk;

/**
 * Desk for Tic Tac Toe game.
 */
public class SimpleDesk implements Desk {

    private final int[][] desk;
    private final int size;
    private int freeCellCounter;

    public SimpleDesk(int size) {
        this.size = size;
        desk = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                desk[y][x] = '-';
            }
        }
        freeCellCounter = size * size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setX(int x, int y) {
        if (checkAvailabilityToMove(x, y)) {
            desk[y][x] = 'x';
            freeCellCounter--;
        }
    }

    @Override
    public void setO(int x, int y) {
        if (checkAvailabilityToMove(x, y)) {
            desk[y][x] = 'o';
            freeCellCounter--;
        }
    }

    @Override
    public String show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < desk[0].length; i++) {
            for (int j = 0; j < desk.length; j++) {
                sb.append((char) desk[i][j]).append("  ");
            }
            if (i != desk[0].length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public char getFigure(int x, int y) {
        return (char) desk[y][x];
    }

    @Override
    public boolean hasFreeCell() {
        return freeCellCounter > 0;
    }

    @Override
    public int[][] getDesk() {
        return desk;
    }

    @Override
    public boolean checkAvailabilityToMove(int x, int y) {
        return x < getSize() && y < getSize() && desk[y][x] == '-';
    }
}
