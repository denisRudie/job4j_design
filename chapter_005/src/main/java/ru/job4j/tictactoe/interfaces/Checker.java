package ru.job4j.tictactoe.interfaces;

public interface Checker {

    boolean isWin(PlayerInterface player);

    boolean hasDraw();
}
