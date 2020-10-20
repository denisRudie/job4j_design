package ru.job4j.tictactoe.enums;

public enum Winner {
    Player1("Player1 wins"),
    Player2("Player2 wins"),
    Draw("It's a draw!");

    private final String code;

    Winner(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
