package main.java.entities;

import java.util.Scanner;

public class Player {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] getPlayerGuess(int numDigits, int minValue, int maxValue) {
        try {
            System.out.print("Enter your guess (" + numDigits + " numbers between " + minValue + " and " + maxValue +
                    ", space-separated): ");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");

            if (inputArray.length != numDigits) {
                System.out.println("Invalid input. Please enter " + numDigits + " numbers between " + minValue +
                        " and " + maxValue + ", space-separated. ");
                return getPlayerGuess(numDigits, minValue, maxValue);
            }

            int[] playerGuess = new int[numDigits];
            for (int i = 0; i < numDigits; i++) {
                int number = Integer.parseInt(inputArray[i]);

                if (number < minValue || number > maxValue) {
                    System.out.println(
                            "Invalid input. Please enter numbers between " + minValue + " and " + maxValue + ".");
                    return getPlayerGuess(numDigits, minValue, maxValue);
                }

                playerGuess[i] = number;
            }

            return playerGuess;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter " + numDigits + " numbers between " + minValue +
                    " and " + maxValue + ", space-separated. ");
            return getPlayerGuess(numDigits, minValue, maxValue);
        }
    }
}