package main.java.ui;

import java.util.Scanner;

import main.java.app.GameController;
import main.java.app.MastermindGame;
import main.java.entities.Player;

public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printMenu(MastermindGame game, GameController gameController) {
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
                    game.resetGame();
                    gameController.playGame();
                    break;
                case "2":
                    game.setTwoPlayerMode(true);
                    game.resetGame();
                    playTwoPlayer(game, gameController);
                    break;
                case "3":
                    selectDifficulty(game);
                    break;
                case "4":
                    GameView.printInstructions(scanner);
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
        game.resetGame();
    }

    private static void handleInvalidChoice() {
        System.out.println("Invalid menu choice. Please enter a number between 1 and 4.");
    }

    private static void playTwoPlayer(MastermindGame game, GameController gameController) {
        int[] secretCode = Player.getPlayerGuess(game.getNumDigits(), game.getMinValue(), game.getMaxValue(), true);

        game.setSecretCode(secretCode);
        gameController.playGame();
        game.setGameEnded(true);
    }

}
