package main.java.app;

import java.util.Scanner;
import main.java.entities.CodeGenerator;
import main.java.ui.DifficultyLevel;
import main.java.ui.MenuHandler;

public class MastermindGame {
    // static final Scanner scanner = new Scanner(System.in);
    // Default case (MEDIUM) no need to change the values
    public static int NUM_ATTEMPTS = 10;
    public static final int NUM_DIGITS = 4;
    public static int MIN_VALUE = 1;
    public static int MAX_VALUE = 8;

    private DifficultyLevel difficultyLevel;
    private boolean gameEnded = false;

    private CodeGenerator codeGenerator;
    public int[] secretCode;
    public int[] playerGuess;
    public int attemptsLeft;

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getNumDigits() {
        return NUM_DIGITS;
    }

    public int getMinValue() {
        return MIN_VALUE;
    }

    public int getMaxValue() {
        return MAX_VALUE;
    }

    public int[] getSecretCode() {
        return secretCode;
    }

    public void decrementAttemptsLeft() {
        attemptsLeft--;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MastermindGame game = new MastermindGame();
            GameController gameController = new GameController(game, scanner);
            System.out.println("Welcome to Mastermind!");

            do {
                MenuHandler.printMenu(game, gameController);
                gameController.playGame();

                if (!gameController.askToPlayAgain()) {
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                }

                System.out.println("\nNew Game!\n");
                game.resetGame(scanner);

            } while (true);
        }
    }

    public MastermindGame() {
        codeGenerator = new CodeGenerator();
        secretCode = codeGenerator.generateSecretCode();
        playerGuess = new int[NUM_DIGITS];
        attemptsLeft = NUM_ATTEMPTS;
        difficultyLevel = DifficultyLevel.MEDIUM;
        resetGame(null);
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficultyLevel = difficulty;
    }

    public void resetGame(Scanner scanner) {
        switch (difficultyLevel) {
            case EASY:
                NUM_ATTEMPTS = 12;
                MIN_VALUE = 1;
                MAX_VALUE = 6;
                attemptsLeft = NUM_ATTEMPTS;
                break;
            case MEDIUM:
                // Default case, no need to change the values
                attemptsLeft = NUM_ATTEMPTS;
                break;
            case HARD:
                NUM_ATTEMPTS = 8;
                MIN_VALUE = 1;
                MAX_VALUE = 10;
                attemptsLeft = NUM_ATTEMPTS;
                break;
            default:
                // Set default values for the case when difficulty is not explicitly handled
                NUM_ATTEMPTS = 10;
                MIN_VALUE = 1;
                MAX_VALUE = 8;
                attemptsLeft = NUM_ATTEMPTS;
                break;
        }
        secretCode = new CodeGenerator().generateSecretCode();
        playerGuess = new int[NUM_DIGITS];
    }

    public boolean containsNumber(int number, int[] array, Scanner scanner) {
        return isNumberInArray(number, array, scanner);
    }

    public boolean isNumberInArray(int number, int[] array, Scanner scanner) {
        for (int value : array) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

}