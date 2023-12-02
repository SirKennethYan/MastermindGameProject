package main.java.ui;

import java.util.Scanner;

import main.java.app.GameController;
import main.java.entities.GameState;
import main.java.entities.Player;

public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printMenu(GameState gameState, GameController gameController) {
        boolean gameEnded = false;

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

    private static void exitGame() {
        System.out.println("Exiting the game. Goodbye!");
        System.exit(0);
    }

    private static void playSinglePlayer(GameState gameState, GameController gameController) {
        gameState.resetGame();
        gameController.playGame();
    }

    private static void playTwoPlayer(GameState gameState, GameController gameController) {
        int[] secretCode = Player.getPlayerGuess(gameState.getNumDigits(), gameState.getMinValue(),
                gameState.getMaxValue(), true);

        gameState.setSecretCode(secretCode);
        gameController.playGame();
        gameState.setGameEnded(true);
    }

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

    private static void setDifficulty(GameState gameState, DifficultyLevel difficulty) {
        gameState.setDifficulty(difficulty);
        System.out.println("Difficulty set to " + difficulty);
    }

    private static void showInstructions() {
        GameView.printInstructions(scanner);
    }

    private static void handleInvalidChoice() {
        System.out.println("Invalid menu choice. Please enter a number between 0 and 4.");
    }
}
