package main.java.ui;

import java.util.Scanner;

import main.java.resources.GameInstructions;

public class GameView {
    public static void printMainMenu() {
        System.out.println("0. Exit Game");
        System.out.println("1. 1-Player");
        System.out.println("2. 2-Player");
        System.out.println("3. Change Difficulty");
        System.out.println("4. View Instructions");
    }

    public static void printInstructions(Scanner scanner) {
        GameInstructions.printInstructions();
        waitForEnter(scanner);
    }

    public static void printDifficultyMenu() {
        System.out.println("Select Difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
    }

    public static void waitForEnter(Scanner scanner) {
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
