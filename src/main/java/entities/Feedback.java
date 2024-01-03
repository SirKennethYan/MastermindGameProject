package main.java.entities;

import main.java.app.MastermindGame;

// Provides methods for feedback between a secret code and a player's guess.
public class Feedback {

    // Calculates the feedback based on the secret code and player's guess.
    public static int[] getFeedback(int[] secretCode, int[] playerGuess) {
        int correctNumber = 0;
        int correctPosition = 0;

        // Keep track of which digits in secretCode/playerGuess have
        // already been matched.
        boolean[] secretCodeMatched = new boolean[MastermindGame.NUM_DIGITS];
        boolean[] playerGuessMatched = new boolean[MastermindGame.NUM_DIGITS];

        // Iterate through each digit in the player's guess and compare it with each
        // digit in the secret code.
        for (int i = 0; i < MastermindGame.NUM_DIGITS; i++) {
            for (int j = 0; j < MastermindGame.NUM_DIGITS; j++) {
                // Check if the current digit in the player's guess matches the digit in the
                // secret code and if it is in the correct position (i.e., both indices are
                // equal).
                if (!playerGuessMatched[j] && playerGuess[j] == secretCode[i] && j == i) {
                    // Increment the count of correct positions, mark the corresponding digits as
                    // matched.
                    correctPosition++;
                    secretCodeMatched[i] = true;
                    playerGuessMatched[i] = true;
                } else if (playerGuess[j] == secretCode[i]) {
                    // If the digits match but are not in the correct position, increment the count
                    // of correct numbers.
                    correctNumber++;
                }
            }
        }

        return new int[] { correctNumber, correctPosition };
    }

    // Method to display feedback for a Mastermind game based on correct number and
    // position.
    public static void displayFeedback(int[] feedback) {
        int correctNumber = feedback[0];
        int correctPosition = feedback[1];

        StringBuilder feedbackString = new StringBuilder("Secret Code: ");

        for (int i = 0; i < correctPosition; i++) {
            feedbackString.append("O "); // (ohs) for correct position.
        }
        for (int i = 0; i < correctNumber; i++) {
            feedbackString.append("_ "); // Underscore for correct number (wrong position).
        }

        int incorrectNumber = MastermindGame.NUM_DIGITS - correctNumber - correctPosition;
        for (int i = 0; i < incorrectNumber; i++) {
            feedbackString.append("X "); // (ex's) for incorrect numbers.
        }

        System.out.println(feedbackString.toString() + "\n\n----------------------------");
    }

}