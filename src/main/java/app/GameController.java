package main.java.app;

import java.util.Arrays;
import java.util.Scanner;
import main.java.entities.Feedback;
import main.java.entities.GameState;
import main.java.entities.Player;
import main.java.ui.MenuHandler;

public class GameController {
    private GameState gameState;
    private Scanner scanner;

    public GameController(GameState gameState, Scanner scanner) {
        this.gameState = gameState;
        this.scanner = scanner;
    }

    public void playGame() {
        // Implement the logic to play the game using the MastermindGame object
        gameState.resetGame(); // Ensure the game is reset before starting
        boolean firstAttempt = true;

        while (gameState.getAttemptsLeft() > 0) {
            if (firstAttempt) {
                System.out.println("Starting Game...Guess the Secret Code!");
                firstAttempt = false;
            }

            System.out.println("Attempts left: " + gameState.getAttemptsLeft());
            int[] playerGuess = Player.getPlayerGuess(gameState.getNumDigits(), gameState.getMinValue(),
                    gameState.getMaxValue(), firstAttempt);

            // Print statements for demo purposes
            System.out.println("Secret code: " + Arrays.toString(gameState.getSecretCode()));

            if (playerGuess == null) {
                System.out.println("Invalid input. Please enter " + gameState.getNumDigits() + " numbers between "
                        + gameState.getMinValue() + " and " + gameState.getMaxValue() + ".");
                continue;
            }

            int[] feedbackResult = Feedback.getFeedback(gameState.getSecretCode(), playerGuess);
            Feedback.displayFeedback(feedbackResult);

            if (feedbackResult[1] == gameState.getNumDigits()) {
                System.out.println("Congratulations! You guessed the correct code!");

                if (askToPlayAgain()) {
                    firstAttempt = true;
                    break;
                } else {
                    return;
                }
            }

            gameState.decrementAttemptsLeft();
        }

        if (gameState.getAttemptsLeft() == 0 && !gameState.isGameEnded()) {
            System.out.println("Sorry, you've run out of attempts. The correct code was: "
                    + Arrays.toString(gameState.getSecretCode()));
            System.out.println("New Game? ");
            System.out.println();
        }
    }

    public boolean askToPlayAgain() {
        System.out.println("Do you want to play again?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                if (choice < 1 || choice > 2) {
                    System.out.println("Invalid choice. Please enter 1 for 'Yes' or 2 for 'No'.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (1 or 2).");
            }
        }

        if (choice == 1) {
            if (gameState.isTwoPlayerMode()) {
                // Set two-player mode and reset the game
                gameState.setTwoPlayerMode(true);
                gameState.resetGame();
                MenuHandler.printMenu(gameState, this);
            } else {
                // Single player, play the game
                playGame();
            }
            return true;
        } else {
            System.out.println("Exiting the game. Goodbye!");
            System.exit(0);
            return false;
        }
    }

}
