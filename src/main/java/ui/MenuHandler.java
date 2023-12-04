package main.java.ui;

import java.util.Scanner;

import main.java.app.GameController;
import main.java.entities.GameState;
import main.java.entities.Player;

// Responsible for managing the interaction between the user and the game menu.
public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printMenu(GameState gameState, GameController gameController) {
        boolean gameEnded = false;
        // Prints the main game menu in a loop until the game is ended.
        while (!gameEnded) {
            GameView.printMainMenu();

            String menuChoice = scanner.nextLine();
            System.out.println();

            switch (menuChoice) {
                case "0":
                    exitGame();
                    break;
                case "1":
                    playSinglePlayer(gameState, gameController);
                    break;
                case "2":
                    gameState.setTwoPlayerMode(true);
                    gameState.resetGame();
                    playTwoPlayer(gameState, gameController);
                    break;
                case "3":
                    selectDifficulty(gameState);
                    break;
                case "4":
                    showInstructions();
                    break;
                default:
                    handleInvalidChoice();
                    break;
            }

            gameEnded = gameState.isGameEnded();
        }
    }

    // Exits the game and prints a farewell message.
    private static void exitGame() {
        System.out.println("Exiting the game. Goodbye!");
        System.exit(0);
    }

    // Resets the game state and starts the game controller for single-player mode.
    private static void playSinglePlayer(GameState gameState, GameController gameController) {
        gameState.resetGame();
        gameController.playGame();
    }

    // Prompts the CodeMaker to enter a secret code, sets it in the game state,
    // and starts the game controller for two-player mode.
    private static void playTwoPlayer(GameState gameState, GameController gameController) {
        int[] secretCode = Player.getPlayerGuess(gameState.getNumDigits(), gameState.getMinValue(),
                gameState.getMaxValue(), true);

        gameState.setSecretCode(secretCode);
        gameController.playGame();
        gameState.setGameEnded(true);
    }

    // Allows the user to select the game difficulty, resetting the game afterward.
    private static void selectDifficulty(GameState gameState) {
        GameView.printDifficultyMenu();

        String difficultyChoice = scanner.nextLine();

        switch (difficultyChoice) {
            case "1":
                setDifficulty(gameState, DifficultyLevel.EASY);
                break;
            case "2":
                setDifficulty(gameState, DifficultyLevel.MEDIUM);
                break;
            case "3":
                setDifficulty(gameState, DifficultyLevel.HARD);
                break;
            default:
                System.out.println("Invalid difficulty choice. Please enter a number (1, 2, or 3). ");
                break;
        }
        gameState.resetGame();
    }

    // Sets the game difficulty in the game state.
    private static void setDifficulty(GameState gameState, DifficultyLevel difficulty) {
        gameState.setDifficulty(difficulty);
        System.out.println("Difficulty set to " + difficulty);
    }

    // Displays game instructions using the GameView class.
    private static void showInstructions() {
        GameView.printInstructions(scanner);
    }

    // Prints a message for invalid menu choices.
    private static void handleInvalidChoice() {
        System.out.println("Invalid menu choice. Please enter a number between 0 and 4.");
    }
}
