package main.java.entities;

import main.java.app.MastermindGame;

public class Feedback {

    public static int[] getFeedback(int[] secretCode, int[] playerGuess) {
        int correctNumber = 0;
        int correctPosition = 0;

        // Arrays to keep track of which digits in secretCode and playerGuess have
        // already been matched
        boolean[] secretCodeMatched = new boolean[MastermindGame.NUM_DIGITS];
        boolean[] playerGuessMatched = new boolean[MastermindGame.NUM_DIGITS];

        // Check for correct positions
        for (int i = 0; i < MastermindGame.NUM_DIGITS; i++) {
            if (playerGuess[i] == secretCode[i]) {
                correctPosition++;
                secretCodeMatched[i] = true;
                playerGuessMatched[i] = true;
            }
        }

        // Then, check for correct numbers in incorrect positions
        for (int i = 0; i < MastermindGame.NUM_DIGITS; i++) {
            if (!secretCodeMatched[i]) {
                for (int j = 0; j < MastermindGame.NUM_DIGITS; j++) {
                    if (!playerGuessMatched[j] && playerGuess[j] == secretCode[i]) {
                        correctNumber++;
                        playerGuessMatched[j] = true;
                        break; // Break out of the inner loop once a match is found
                    }
                }
            }
        }

        return new int[] { correctNumber, correctPosition };
    }

    public static void displayFeedback(int[] feedback) {
        System.out.println("Feedback: " + feedback[0] + " correct number(s), " + feedback[1] + " correct position(s)");
        System.out.println("----------------------------");
    }
}