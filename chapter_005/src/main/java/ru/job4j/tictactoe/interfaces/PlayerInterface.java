package ru.job4j.tictactoe.interfaces;

import ru.job4j.tictactoe.enums.PlayerFigure;

public interface PlayerInterface {

    void move(Desk desk);

    PlayerFigure getFigure();
}
