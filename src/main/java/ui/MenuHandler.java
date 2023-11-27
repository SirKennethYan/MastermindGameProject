package main.java.ui;

import java.util.Scanner;

import main.java.app.MastermindGame;
import main.java.instructions.GameInstructions;

public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printMenu(MastermindGame game) {

        while (true) {
            System.out.println("1. Start Game");
            System.out.println("2. View Instructions");

            String menuChoice = scanner.nextLine();
            System.out.println();

            switch (menuChoice) {
                case "1":
                    game.playGame();
                    break;
                case "2":
                    GameInstructions.printInstructions();
                    waitForEnter();
                    break;
                default:
                    try {
                        Integer.parseInt(menuChoice);
                        System.out.println("Invalid menu choice. Please enter a number (1 or 2). ");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number (1 or 2). ");
                    }
                    break;
            }
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