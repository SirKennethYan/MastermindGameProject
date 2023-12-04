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

        // Check for correct positions.
        for (int i = 0; i < MastermindGame.NUM_DIGITS; i++) {
            if (playerGuess[i] == secretCode[i]) {
                correctPosition++;
                secretCodeMatched[i] = true;
                playerGuessMatched[i] = true;
            }
        }

        // Then, check for correct numbers in incorrect positions.
        for (int i = 0; i < MastermindGame.NUM_DIGITS; i++) {
            if (!secretCodeMatched[i]) {
                for (int j = 0; j < MastermindGame.NUM_DIGITS; j++) {
                    if (!playerGuessMatched[j] && playerGuess[j] == secretCode[i]) {
                        correctNumber++;
                        playerGuessMatched[j] = true;
                        break;
                    }
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