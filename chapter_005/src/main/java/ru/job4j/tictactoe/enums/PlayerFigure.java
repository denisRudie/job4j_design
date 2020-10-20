package ru.job4j.tictactoe.enums;

public enum PlayerFigure {
    X('x'),
    O('o');

    private final char code;

    PlayerFigure(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
