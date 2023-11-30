package main.java.app;

import java.util.Arrays;
import java.util.Scanner;
import main.java.entities.Feedback;
import main.java.entities.Player;

public class GameController {
    private MastermindGame mastermindGame;
    private Scanner scanner;

    public GameController(MastermindGame mastermindGame, Scanner scanner) {
        this.mastermindGame = mastermindGame;
        this.scanner = scanner;
    }

    public void playGame() {
        // Implement the logic to play the game using the MastermindGame object
        mastermindGame.resetGame(null); // Ensure the game is reset before starting
        boolean firstAttempt = true;

        while (mastermindGame.getAttemptsLeft() > 0) {
            if (firstAttempt) {
                System.out.println("Starting Game...Guess the Secret Code!");
                firstAttempt = false;
            }

            System.out.println("Attempts left: " + mastermindGame.getAttemptsLeft());
            int[] playerGuess = Player.getPlayerGuess(mastermindGame.getNumDigits(), mastermindGame.getMinValue(),
                    mastermindGame.getMaxValue());

            // Print statements for demo purposes
            System.out.println("Secret code: " + Arrays.toString(mastermindGame.getSecretCode()));

            if (playerGuess == null) {
                System.out.println("Invalid input. Please enter " + mastermindGame.getNumDigits() + " numbers between "
                        + mastermindGame.getMinValue() + " and " + mastermindGame.getMaxValue() + ".");
                continue;
            }

            int[] feedbackResult = Feedback.getFeedback(mastermindGame.getSecretCode(), playerGuess);
            Feedback.displayFeedback(feedbackResult);

            if (feedbackResult[1] == mastermindGame.getNumDigits()) {
                System.out.println("Congratulations! You guessed the correct code!");

                if (askToPlayAgain()) {
                    firstAttempt = true;
                    break;
                } else {
                    return;
                }
            }

            mastermindGame.decrementAttemptsLeft();
        }

        if (mastermindGame.getAttemptsLeft() == 0 && !mastermindGame.isGameEnded()) {
            System.out.println("Sorry, you've run out of attempts. The correct code was: "
                    + Arrays.toString(mastermindGame.getSecretCode()));
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
            playGame();
            return true;
        } else {
            System.out.println("Exiting the game. Goodbye!");
            System.exit(0);
            return false;
        }
    }

}
