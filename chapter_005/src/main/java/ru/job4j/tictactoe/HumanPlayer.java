package ru.job4j.tictactoe;

import ru.job4j.tictactoe.enums.PlayerFigure;
import ru.job4j.tictactoe.interfaces.Desk;
import ru.job4j.tictactoe.interfaces.HumanInput;
import ru.job4j.tictactoe.interfaces.PlayerInterface;

/**
 * Human player.
 */
public class HumanPlayer implements PlayerInterface {
    private final PlayerFigure figure;
    private final HumanInput humanInput;

    public HumanPlayer(PlayerFigure playerFigure, HumanInput humanInput) {
        this.figure = playerFigure;
        this.humanInput = humanInput;
    }

    @Override
    public void move(Desk desk) {
        int[] coordinates = humanInput.getCommandToMove(desk);

        if (figure == PlayerFigure.O) {
            desk.setO(coordinates[0], coordinates[1]);
        } else if (figure == PlayerFigure.X) {
            desk.setX(coordinates[0], coordinates[1]);
        }
    }

    @Override
    public PlayerFigure getFigure() {
        return figure;
    }
}
