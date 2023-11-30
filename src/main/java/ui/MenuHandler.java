package main.java.ui;

import java.util.Scanner;

import main.java.app.GameController;
import main.java.app.MastermindGame;

public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printMenu(MastermindGame game, GameController gameController) {
        boolean gameEnded = false;

        while (!gameEnded) {
            GameView.printMainMenu();

            String menuChoice = scanner.nextLine();
            System.out.println();

            switch (menuChoice) {
                case "1":
                    game.resetGame();
                    gameController.playGame();
                    break;
                case "2":
                    GameView.printInstructions(scanner);
                    break;
                case "3":
                    selectDifficulty(game);
                    break;
                case "4":
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    handleInvalidChoice();
                    break;
            }

            gameEnded = game.isGameEnded();
        }
    }

    private static void selectDifficulty(MastermindGame game) {
        GameView.printDifficultyMenu();

        String difficultyChoice = scanner.nextLine();

        switch (difficultyChoice) {
            case "1":
                game.setDifficulty(DifficultyLevel.EASY);
                System.out.println("Difficulty set to Easy");
                break;
            case "2":
                game.setDifficulty(DifficultyLevel.MEDIUM);
                System.out.println("Difficulty set to Medium");
                break;
            case "3":
                game.setDifficulty(DifficultyLevel.HARD);
                System.out.println("Difficulty set to Hard");
                break;
            default:
                System.out.println("Invalid difficulty choice. Please enter a number (1, 2, or 3). ");
                break;
        }
    }

    private static void handleInvalidChoice() {
        System.out.println("Invalid menu choice. Please enter a number between 1 and 4.");
    }
}
