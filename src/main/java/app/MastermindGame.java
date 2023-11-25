package main.java.app;

import java.util.Arrays;
import java.util.Scanner;
import main.java.entities.CodeGenerator;
import main.java.entities.Feedback;
import main.java.entities.Player;

public class MastermindGame {
    private static final Scanner scanner = new Scanner(System.in);
    public static final int NUM_ATTEMPTS = 10;
    public static final int NUM_DIGITS = 4;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 8;
    private CodeGenerator codeGenerator;
    public int[] secretCode;
    public int[] playerGuess;
    public int attemptsLeft;

    public MastermindGame() {
        codeGenerator = new CodeGenerator();
        secretCode = codeGenerator.generateSecretCode();
        playerGuess = new int[NUM_DIGITS];
        attemptsLeft = NUM_ATTEMPTS;
    }

    public static void main(String[] args) {
        MastermindGame game = new MastermindGame();
        game.playGame();
        scanner.close();
    }

    public void playGame() {
        System.out.println("Welcome to Mastermind!");

        while (attemptsLeft > 0) {
            System.out.println("Attempts left: " + attemptsLeft);
            playerGuess = Player.getPlayerGuess(NUM_DIGITS, MIN_VALUE, MAX_VALUE);
            int[] feedbackResult = Feedback.getFeedback(secretCode, playerGuess);
            Feedback.displayFeedback(feedbackResult);

            if (feedbackResult[1] == NUM_DIGITS) {
                System.out.println("Congratulations! You guessed the correct code!");
                break;
            }

            attemptsLeft--;
        }

        if (attemptsLeft == 0) {
            System.out
                    .println("Sorry, you've run out of attempts. The correct code was: " + Arrays.toString(secretCode));
        }
        System.out.println("Game ended.");
    }

    public boolean containsNumber(int number, int[] array) {
        for (int value : array) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }

}
