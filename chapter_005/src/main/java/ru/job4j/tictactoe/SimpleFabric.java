package ru.job4j.tictactoe;

import ru.job4j.tictactoe.enums.PlayerFigure;
import ru.job4j.tictactoe.interfaces.Desk;
import ru.job4j.tictactoe.interfaces.Fabric;
import ru.job4j.tictactoe.interfaces.PlayerInterface;

import java.util.Properties;

public class SimpleFabric implements Fabric {

    private final Properties prop;

    public SimpleFabric(Properties prop) {
        this.prop = prop;
    }

    @Override
    public PlayerInterface createFirstPlayer() {
        PlayerInterface rsl = null;
        String fp = prop.getProperty("firstPlayer");
        String fpf = prop.getProperty("firstPlayerFigure");
        if (fp.equals("PC")) {
            if (fpf.equals("O")) {
                rsl = new SimpleAI(PlayerFigure.O);
            } else if (fpf.equals("X")) {
                rsl = new SimpleAI(PlayerFigure.X);
            }
        } else if (fp.equals("Human")) {
            if (fpf.equals("O")) {
                rsl = new HumanPlayer(PlayerFigure.O, new SimpleHumanInput());
            } else if (fpf.equals("X")) {
                rsl = new HumanPlayer(PlayerFigure.X, new SimpleHumanInput());
            }
        }
        return rsl;
    }

    @Override
    public PlayerInterface createSecondPlayer() {
        PlayerInterface rsl = null;
        String sp = prop.getProperty("secondPlayer");
        String fpf = prop.getProperty("firstPlayerFigure");
        if (sp.equals("PC")) {
            if (fpf.equals("O")) {
                rsl = new SimpleAI(PlayerFigure.X);
            } else if (fpf.equals("X")) {
                rsl = new SimpleAI(PlayerFigure.O);
            }
        } else if (sp.equals("Human")) {
            if (fpf.equals("O")) {
                rsl = new HumanPlayer(PlayerFigure.X, new SimpleHumanInput());
            } else if (fpf.equals("X")) {
                rsl = new HumanPlayer(PlayerFigure.O, new SimpleHumanInput());
            }
        }
        return rsl;
    }

    @Override
    public Desk createDesk() {
        String d = prop.getProperty("deskSize");
        return new SimpleDesk(Integer.parseInt(d));
    }
}
