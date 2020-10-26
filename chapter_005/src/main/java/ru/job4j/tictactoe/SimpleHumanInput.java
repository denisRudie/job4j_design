package ru.job4j.tictactoe;

import ru.job4j.tictactoe.interfaces.Desk;
import ru.job4j.tictactoe.interfaces.HumanInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Console command reader for Human player.
 */
public class SimpleHumanInput implements HumanInput {
    private final Pattern p = Pattern.compile("(\\d) (\\d)");

    @Override
    public int[] getCommandToMove(Desk desk) {
        int[] rsl = new int[2];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter cell for moving. Format: x y");

            boolean turnFinish = false;
            while (!turnFinish) {
                String in = reader.readLine();
                Matcher m = p.matcher(in);

                if (m.matches()) {
                    int x = Integer.parseInt(m.group(1));
                    int y = Integer.parseInt(m.group(2));

                    if (desk.checkAvailabilityToMove(x, y)) {
                        rsl[0] = x;
                        rsl[1] = y;
                        turnFinish = true;
                    } else {
                        System.out.println("This cell doesn't exist or already taken");
                    }
                } else {
                    System.out.println("Unexpected cell value");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
