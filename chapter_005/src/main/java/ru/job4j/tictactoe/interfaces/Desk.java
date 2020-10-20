package ru.job4j.tictactoe.interfaces;

public interface Desk {

    String show();

    char getFigure(int x, int y);

    int getSize();

    void setX(int x, int y);

    void setO(int x, int y);

    boolean hasFreeCell();

    int[][] getDesk();

    boolean checkAvailabilityToMove(int x, int y);
}
