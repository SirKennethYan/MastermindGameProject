package main.java.entities;

import java.util.Scanner;

public class Player {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] getPlayerGuess(int numDigits, int minValue, int maxValue, boolean codeMaker) {
        int[] playerGuess = new int[numDigits];

        // Print the prompt for Code Maker only
        if (codeMaker) {
            System.out.print("(Code Maker), enter the super secret code: ");
        } else {
            System.out.print("Player Guess: ");
        }

        // Read player input
        String input = scanner.nextLine();

        // Split the input string into an array of integers
        String[] inputArray = input.split(" ");
        if (inputArray.length != numDigits) {
            System.out.println("Invalid input. Please enter " + numDigits + " numbers.");
            return null;
        }

        try {
            // Parse the input into an array of integers
            for (int i = 0; i < numDigits; i++) {
                playerGuess[i] = Integer.parseInt(inputArray[i]);
                // Validate the input range
                if (playerGuess[i] < minValue || playerGuess[i] > maxValue) {
                    System.out.println("Invalid input. Numbers must be between " + minValue + " and " + maxValue + ".");
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
            return null;
        }

        return playerGuess;
    }

}