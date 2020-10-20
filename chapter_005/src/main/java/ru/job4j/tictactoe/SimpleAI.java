package ru.job4j.tictactoe;

import ru.job4j.tictactoe.enums.PlayerFigure;
import ru.job4j.tictactoe.interfaces.Desk;
import ru.job4j.tictactoe.interfaces.PlayerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * PC player.
 */
public class SimpleAI implements PlayerInterface {
    private final PlayerFigure figure;

    public SimpleAI(PlayerFigure figure) {
        this.figure = figure;
    }

    @Override
    public void move(Desk desk) {
        List<Cell> freeCells = new ArrayList<>();
        int[][] gameDesk = desk.getDesk();

        for (int y = 0; y < gameDesk[0].length; y++) {
            for (int x = 0; x < gameDesk.length; x++) {
                   if (desk.getFigure(x, y) == '-') {
                       freeCells.add(new Cell(x, y));
                   }
            }
        }

        Cell randomCell = freeCells.get(
                ThreadLocalRandom.current().nextInt(0, freeCells.size()));

        if (figure == PlayerFigure.O) {
            desk.setO(randomCell.x, randomCell.y);
        } else if (figure == PlayerFigure.X) {
            desk.setX(randomCell.x, randomCell.y);
        }
    }

    @Override
    public PlayerFigure getFigure() {
        return figure;
    }

    private static class Cell {
        private final int x;
        private final int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
