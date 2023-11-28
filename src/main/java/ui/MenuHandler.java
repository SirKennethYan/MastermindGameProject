package main.java.ui;

import java.util.Scanner;

import main.java.app.MastermindGame;
import main.java.instructions.GameInstructions;

public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printMenu(MastermindGame game) {

        boolean gameEnded = false;

        while (!gameEnded) {
            System.out.println("1. Start Game");
            System.out.println("2. View Instructions");
            System.out.println("3. Select Difficulty");
            System.out.println("4. Exit Game");

            String menuChoice = scanner.nextLine();
            System.out.println();

            switch (menuChoice) {
                case "0":
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                    break;
                case "1":
                    game.resetGame();
                    game.playGame();
                    break;
                case "2":
                    GameInstructions.printInstructions();
                    waitForEnter();
                    break;
                case "3":
                    selectDifficulty(game);
                    break;
                case "4":
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    try {
                        int choice = Integer.parseInt(menuChoice);
                        if (choice < 0 || choice > 4) {
                            System.out.println("Invalid menu choice. Please enter a number between 0 and 4.");
                        } else {
                            System.out.println("Invalid menu choice. Please enter a valid number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number (0 to 4). ");
                    }
                    break;
            }

            gameEnded = game.isGameEnded();
        }
    }

    private static void selectDifficulty(MastermindGame game) {
        System.out.println("Select Difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

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

    private static void waitForEnter() {
        // Continue reading input until the ENTER key is pressed
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break; // Break the loop if ENTER is pressed
            } else {
                System.out.println("Invalid key, press ENTER to return to menu");
            }
        }
    }

}