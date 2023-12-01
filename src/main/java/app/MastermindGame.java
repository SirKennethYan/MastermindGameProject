package main.java.app;

import java.util.Scanner;
import main.java.entities.CodeGenerator;
import main.java.ui.DifficultyLevel;
import main.java.ui.MenuHandler;

public class MastermindGame {
    // Default case (MEDIUM) no need to change the values
    public static int NUM_ATTEMPTS = 10;
    public static final int NUM_DIGITS = 4;
    public static int MIN_VALUE = 1;
    public static int MAX_VALUE = 8;

    private DifficultyLevel difficultyLevel;
    private boolean gameEnded = false;
    private boolean twoPlayerMode = false;

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
                game.resetGame();

            } while (true);
        }
    }

    public MastermindGame() {
        codeGenerator = new CodeGenerator();
        secretCode = codeGenerator.generateSecretCode();
        playerGuess = new int[NUM_DIGITS];
        // attemptsLeft = NUM_ATTEMPTS;
        difficultyLevel = DifficultyLevel.MEDIUM;
        resetGame();
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficultyLevel = difficulty;
    }

    public void setSecretCode(int[] code) {
        this.secretCode = code;
    }

    public void setTwoPlayerMode(boolean twoPlayerMode) {
        this.twoPlayerMode = twoPlayerMode;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public void resetGame() {
        if (!twoPlayerMode) {
            switch (difficultyLevel) {
                case EASY:
                    NUM_ATTEMPTS = 12;
                    MIN_VALUE = 1;
                    MAX_VALUE = 6;
                    break;
                case MEDIUM:
                    // Default case, no need to change the values
                    NUM_ATTEMPTS = 10;
                    MIN_VALUE = 1;
                    MAX_VALUE = 8;
                    break;
                case HARD:
                    NUM_ATTEMPTS = 8;
                    MIN_VALUE = 1;
                    MAX_VALUE = 10;
                    break;
            }

            codeGenerator = new CodeGenerator(); // Ensure you have a field for CodeGenerator
            secretCode = codeGenerator.generateSecretCode();

            attemptsLeft = NUM_ATTEMPTS;
        }

        playerGuess = new int[NUM_DIGITS];
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public boolean isTwoPlayerMode() {
        return twoPlayerMode;
    }

}