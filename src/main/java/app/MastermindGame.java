package main.java.app;

import java.util.Arrays;
import java.util.Scanner;
import main.java.entities.CodeGenerator;
import main.java.entities.Feedback;
import main.java.entities.Player;
import main.java.ui.DifficultyLevel;
import main.java.ui.MenuHandler;

public class MastermindGame {
    private static final Scanner scanner = new Scanner(System.in);
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

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MastermindGame game = new MastermindGame();
            System.out.println("Welcome to Mastermind!");

            do {
                MenuHandler.printMenu(game);
                game.playGame();

                if (!game.askToPlayAgain()) {
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
        attemptsLeft = NUM_ATTEMPTS;
        difficultyLevel = DifficultyLevel.MEDIUM;
        resetGame();
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficultyLevel = difficulty;
    }

    public void resetGame() {
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

    public void playGame() {
        resetGame(); // Reset the game when the difficulty changes
        boolean firstAttempt = true; // flag set to true for the first game

        while (attemptsLeft > 0) {
            if (firstAttempt) {
                System.out.println("Starting Game...Guess the Secret Code!");
                firstAttempt = false; // Set the flag to false after the first attempt
            }

            System.out.println("Attempts left: " + attemptsLeft);
            playerGuess = Player.getPlayerGuess(NUM_DIGITS, MIN_VALUE, MAX_VALUE);
            // Print statements for demo purposes
            // System.out.println("Player guess: " + Arrays.toString(playerGuess));
            System.out.println("Secret code: " + Arrays.toString(secretCode));

            if (playerGuess == null) {
                System.out.println("Invalid input. Please enter " + NUM_DIGITS + " numbers between "
                        + MIN_VALUE + " and " + MAX_VALUE + ".");
                continue; // Restart the loop to get a valid input
            }

            int[] feedbackResult = Feedback.getFeedback(secretCode, playerGuess);
            Feedback.displayFeedback(feedbackResult);

            if (feedbackResult[1] == NUM_DIGITS) {
                System.out.println("Congratulations! You guessed the correct code!");

                if (askToPlayAgain()) {
                    firstAttempt = true; // flag set to true for the next game
                    break; // Exit the loop if the user wants to play again
                } else {
                    return; // Return from the method if the user chooses not to play again
                }
            }

            attemptsLeft--;
        }

        if (attemptsLeft == 0 && !isGameEnded()) {
            System.out
                    .println("Sorry, you've run out of attempts. The correct code was: " + Arrays.toString(secretCode));
            System.out.println("New Game? ");
            System.out.println();
        }
    }

    private boolean askToPlayAgain() {
        System.out.println("Do you want to play again?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = getUserChoice();

        if (choice == 1) {
            playGame();
            return true;
        } else {
            System.out.println("Exiting the game. Goodbye!");
            System.exit(0); // Exit the program if the user chooses not to play again
            return false; // This line is not strictly necessary but included for clarity
        }
    }

    private int getUserChoice() {
        int choice = 0;

        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 2) {
                System.out.println("Invalid choice. Please enter 1 for 'yes' or 2 for 'no'.");
                return getUserChoice(); // Recursively call the method for invalid choices
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getUserChoice(); // Recursively call the method for invalid input
        }

        return choice;
    }

    public boolean containsNumber(int number, int[] array) {
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