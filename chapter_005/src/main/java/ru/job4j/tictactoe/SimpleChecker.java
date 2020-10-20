package ru.job4j.tictactoe;

import ru.job4j.tictactoe.enums.PlayerFigure;
import ru.job4j.tictactoe.interfaces.Checker;
import ru.job4j.tictactoe.interfaces.Desk;
import ru.job4j.tictactoe.interfaces.PlayerInterface;

/**
 * Checking for win or draw.
 */
public class SimpleChecker implements Checker {

    private final Desk desk;

    public SimpleChecker(Desk desk) {
        this.desk = desk;
    }

    @Override
    public boolean isWin(PlayerInterface player) {
        PlayerFigure figure = player.getFigure();

        boolean diagFromTopRight = true;
        boolean diagFromTopLeft = true;
        for (int y = 0; y < desk.getSize(); y++) {
            boolean horizontal = true;
            boolean vertical = true;
            for (int x = 0; x < desk.getSize(); x++) {
                horizontal &= desk.getFigure(x, y) == figure.getCode();
                vertical &= desk.getFigure(y, x) == figure.getCode();
            }

            if (horizontal) {
                return true;
            } else if (vertical) {
                return true;
            }

            diagFromTopLeft &= desk.getFigure(y, y) == figure.getCode();
            diagFromTopRight &= desk.getFigure(desk.getSize() - 1 - y, y) == figure.getCode();
        }
        return diagFromTopLeft || diagFromTopRight;
    }

    @Override
    public boolean hasDraw() {
        return !desk.hasFreeCell();
    }
}
