package main.java.ui;

import java.util.Scanner;

import main.java.app.GameController;
import main.java.app.MastermindGame;
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
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                    break;
                case "1":
                    gameState.resetGame();
                    gameController.playGame();
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
                    GameView.printInstructions(scanner);
                    break;
                default:
                    handleInvalidChoice();
                    break;
            }

            gameEnded = gameState.isGameEnded();
        }
    }

    private static void selectDifficulty(GameState gameState) {
        GameView.printDifficultyMenu();

        String difficultyChoice = scanner.nextLine();

        switch (difficultyChoice) {
            case "1":
                gameState.setDifficulty(DifficultyLevel.EASY);
                System.out.println("Difficulty set to Easy");
                break;
            case "2":
                gameState.setDifficulty(DifficultyLevel.MEDIUM);
                System.out.println("Difficulty set to Medium");
                break;
            case "3":
                gameState.setDifficulty(DifficultyLevel.HARD);
                System.out.println("Difficulty set to Hard");
                break;
            default:
                System.out.println("Invalid difficulty choice. Please enter a number (1, 2, or 3). ");
                break;
        }
        gameState.resetGame();
    }

    private static void handleInvalidChoice() {
        System.out.println("Invalid menu choice. Please enter a number between 1 and 4.");
    }

    private static void playTwoPlayer(GameState gameState, GameController gameController) {
        int[] secretCode = Player.getPlayerGuess(gameState.getNumDigits(), gameState.getMinValue(),
                gameState.getMaxValue(), true);

        gameState.setSecretCode(secretCode);
        gameController.playGame();
        gameState.setGameEnded(true);
    }

}
