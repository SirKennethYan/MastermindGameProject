package main.java.ui;

import java.util.Scanner;

import main.java.instructions.GameInstructions;

public class GameView {
    public static void printMainMenu() {
        System.out.println("0. Exit Game");
        System.out.println("1. Start Game");
        System.out.println("2. View Instructions");
        System.out.println("3. Select Difficulty");
        System.out.println("4. Two-Player");
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
