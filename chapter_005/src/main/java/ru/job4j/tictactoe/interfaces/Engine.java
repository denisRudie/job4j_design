package ru.job4j.tictactoe.interfaces;

import ru.job4j.tictactoe.enums.Winner;

public interface Engine {

    void init();

    void start();

    Winner getResult();
}
