package ru.job4j.tictactoe.interfaces;

public interface Fabric {

    PlayerInterface createFirstPlayer();

    PlayerInterface createSecondPlayer();

    Desk createDesk();
}
