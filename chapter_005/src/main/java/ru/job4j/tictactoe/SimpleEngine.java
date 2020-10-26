package ru.job4j.tictactoe;

import ru.job4j.tictactoe.enums.Winner;
import ru.job4j.tictactoe.interfaces.Checker;
import ru.job4j.tictactoe.interfaces.Desk;
import ru.job4j.tictactoe.interfaces.Engine;
import ru.job4j.tictactoe.interfaces.PlayerInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Tic Tac Toe game engine.
 */
public class SimpleEngine implements Engine {

    private final Properties pr;
    private Desk desk;
    private PlayerInterface player1;
    private PlayerInterface player2;
    private Checker checker;
    private Winner result;

    public SimpleEngine(Properties prop) {
        this.pr = prop;
    }

    @Override
    public void init() {
        SimpleFabric simpleFabric = new SimpleFabric(pr);
        desk = simpleFabric.createDesk();
        player1 = simpleFabric.createFirstPlayer();
        player2 = simpleFabric.createSecondPlayer();
        checker = new SimpleChecker(desk);
    }

    @Override
    public void start() {
        while (result == null) {
            System.out.println(desk.show());
            System.out.println(System.lineSeparator());
            player1.move(desk);
            System.out.println(desk.show());

            if (checker.hasDraw()) {
                result = Winner.Draw;
                break;
            } else if (checker.isWin(player1)) {
                result = Winner.Player1;
                break;
            }

            System.out.println(System.lineSeparator());
            player2.move(desk);

            if (checker.hasDraw()) {
                System.out.println(desk.show());
                result = Winner.Draw;
                break;
            } else if (checker.isWin(player2)) {
                System.out.println(desk.show());
                result = Winner.Player2;
                break;
            }
        }
    }

    @Override
    public Winner getResult() {
        return result;
    }

    public static void main(String[] args) {
        Properties prop = new Properties();
        try (InputStream is = SimpleEngine.class
                .getClassLoader()
                .getResourceAsStream("tictactoe.properties")) {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Engine engine = new SimpleEngine(prop);
        engine.init();
        engine.start();
        System.out.println(engine.getResult().getCode());
    }
}
