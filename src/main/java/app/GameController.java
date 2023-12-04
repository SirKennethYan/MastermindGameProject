package main.java.app;

import java.util.Arrays;
import java.util.Scanner;
import main.java.entities.Feedback;
import main.java.entities.GameState;
import main.java.entities.Player;
import main.java.ui.MenuHandler;

// Responsible for managing the flow of a Mastermind game. 
public class GameController {
    private GameState gameState;
    private Scanner scanner;

    // Takes a GameState object and a Scanner object as parameters to initialize the
    // game state and input scanner.
    public GameController(GameState gameState, Scanner scanner) {
        this.gameState = gameState;
        this.scanner = scanner;
    }

    // Orchestrates the gameplay loop.
    public void playGame() {
        gameState.resetGame();
        boolean firstAttempt = true;

        while (gameState.getAttemptsLeft() > 0) {
            if (firstAttempt) {
                System.out.println("Starting Game...Guess the Secret Code!");
                firstAttempt = false;
            }

            displayGameInfo();

            int[] playerGuess = getPlayerInput(firstAttempt);

            if (playerGuess == null) {
                continue;
            }

            int[] feedbackResult = Feedback.getFeedback(gameState.getSecretCode(), playerGuess);
            Feedback.displayFeedback(feedbackResult);

            if (checkGameEndCondition(feedbackResult)) {
                break;
            }

            gameState.decrementAttemptsLeft();
        }

        handleGameEnd();
    }

    // Prints information about the game state.
    private void displayGameInfo() {
        System.out.println("Attempts left: " + gameState.getAttemptsLeft());
        // Print statements for demo purposes.
        System.out.println("Secret code: " + Arrays.toString(gameState.getSecretCode()));
    }

    // Obtains player input using the Player class.
    private int[] getPlayerInput(boolean firstAttempt) {
        return Player.getPlayerGuess(gameState.getNumDigits(), gameState.getMinValue(),
                gameState.getMaxValue(), firstAttempt);
    }

    // Checks if the game should end based on the feedback.
    private boolean checkGameEndCondition(int[] feedbackResult) {
        if (feedbackResult[1] == gameState.getNumDigits()) {
            System.out.println("Congratulations! You guessed the correct code!");

            if (askToPlayAgain()) {
                return true;
            } else {
                gameState.setGameEnded(true);
                return true;
            }
        }

        return false;
    }

    // Handles the end of the game, displaying messages if the player runs out of
    // attempts.
    private void handleGameEnd() {
        if (gameState.getAttemptsLeft() == 0 && !gameState.isGameEnded()) {
            System.out.println("Sorry, you've run out of attempts. The correct code was: "
                    + Arrays.toString(gameState.getSecretCode()));
            System.out.println("New Game? ");
            System.out.println();
        }
    }

    // Asks the player if they want to play again and handles the response.
    public boolean askToPlayAgain() {
        System.out.println("Do you want to play again?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = getValidChoice();

        if (choice == 1) {
            handlePlayAgain();
            return true;
        } else {
            handleExitGame();
            return false;
        }
    }

    // Ensures the player's choice is valid (1 for 'Yes' or 2 for 'No').
    private int getValidChoice() {
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

        return choice;
    }

    // Handles the case where the player wants to play again.
    private void handlePlayAgain() {
        if (gameState.isTwoPlayerMode()) {
            gameState.setTwoPlayerMode(true);
            gameState.resetGame();
            MenuHandler.printMenu(gameState, this);
        } else {
            playGame();
        }
    }

    // Handles the case where the player chooses to exit the game.
    private void handleExitGame() {
        System.out.println("Exiting the game. Goodbye!");
        System.exit(0);
    }

}
