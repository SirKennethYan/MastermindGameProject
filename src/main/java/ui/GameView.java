package main.java.ui;

import java.util.Scanner;

import main.java.resources.GameInstructions;

// Responsible for displaying various menus and instructions to the user in a console-based game. 
public class GameView {
    public static void printMainMenu() {
        System.out.println("0. Exit Game");
        System.out.println("1. 1-Player");
        System.out.println("2. 2-Player");
        System.out.println("3. Change Difficulty");
        System.out.println("4. View Instructions");
    }

    // Calls the printInstructions method from GameInstructions class to display
    // game instructions to user.
    public static void printInstructions(Scanner scanner) {
        GameInstructions.printInstructions();
        waitForEnter(scanner);
    }

    // Prints the main menu options for the game.
    public static void printDifficultyMenu() {
        System.out.println("Select Difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
    }

    // Continue reading input until the ENTER key is pressed
    public static void waitForEnter(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            } else {
                System.out.println("Invalid key, press ENTER to return to menu");
            }
        }
    }
}
