package trivia.model.game;

import java.util.Random;
import java.util.Scanner;

public class Dice {

    private static final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public int roll() {
        System.out.print(
            ">> Throw a die and input roll, or [ENTER] to generate a random roll: "
        );
        return getRollInput();
    }

    private int getRollInput() {
        String rollString = scanner.nextLine().trim();
        if (rollString.isEmpty()) {
            return rollRandom();
        }
        if (isValidInput(rollString)) {
            return Integer.parseInt(rollString);
        } else {
            return getRollInput();
        }
    }

    private int rollRandom() {
        return random.nextInt(6) + 1;
    }

    private boolean isValidInput(String rollString) {
        if (isNotNumber(rollString)) {
            System.err.println("Not a number: '" + rollString + "'");
            return false;
        }
        if (isOutOfBound(rollString)) {
            System.err.println("Invalid roll");
            return false;
        }

        return true;
    }

    private boolean isNotNumber(String rollString) {
        return !rollString.matches("\\d+");
    }

    private boolean isOutOfBound(String rollString) {
        int rollNumber = Integer.parseInt(rollString);
        return rollNumber < 1 || rollNumber > 6;
    }
}
