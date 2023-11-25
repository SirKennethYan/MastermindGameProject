package main.java.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class MastermindGame {
    public static final String API_URL = "https://www.random.org/integers";
    private static final Scanner scanner = new Scanner(System.in);
    public static final int NUM_DIGITS = 4;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 8;
    public static final int NUM_ATTEMPTS = 10;
    public int[] secretCode;
    public int[] playerGuess;
    public int attemptsLeft;

    public MastermindGame() {
        secretCode = generateSecretCode();
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
            getPlayerGuess();
            int[] feedback = getFeedback();
            displayFeedback(feedback);

            if (feedback[1] == NUM_DIGITS) {
                System.out.println("Congratulations! You guessed the correct code!");
                break;
            }

            attemptsLeft--;
        }

        if (attemptsLeft == 0) {
            System.out
                    .println("Sorry, you've run out of attempts. The correct code was: " + Arrays.toString(secretCode));
        }
    }

    public void getPlayerGuess() {
        try {
            System.out.print("Enter your guess (4 numbers, space-separated): ");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");

            if (inputArray.length != NUM_DIGITS) {
                System.out.println("Invalid input. Please enter 4 numbers, space-separated. ");
                getPlayerGuess();
                return;
            }

            for (int i = 0; i < NUM_DIGITS; i++) {
                playerGuess[i] = Integer.parseInt(inputArray[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter 4 numbers, space-separated. ");
            e.printStackTrace();
        }
    }

    public int[] getFeedback() {
        int correctNumber = 0;
        int correctPosition = 0;

        // Arrays to keep track of which digits in secretCode and playerGuess have
        // already been matched
        boolean[] secretCodeMatched = new boolean[NUM_DIGITS];
        boolean[] playerGuessMatched = new boolean[NUM_DIGITS];

        // Check for correct positions
        for (int i = 0; i < NUM_DIGITS; i++) {
            if (playerGuess[i] == secretCode[i]) {
                correctPosition++;
                secretCodeMatched[i] = true;
                playerGuessMatched[i] = true;
            }
        }

        // Then, check for correct numbers in incorrect positions
        for (int i = 0; i < NUM_DIGITS; i++) {
            if (!secretCodeMatched[i]) {
                for (int j = 0; j < NUM_DIGITS; j++) {
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

    public void displayFeedback(int[] feedback) {
        System.out.println("Feedback: " + feedback[0] + " correct number(s), " + feedback[1] + " correct position(s)");
        System.out.println("----------------------------");
    }

    public int[] generateSecretCode() {
        int[] code = new int[NUM_DIGITS];
        try {
            URL url = new URL(API_URL + "?num=" + NUM_DIGITS + "&min=" + MIN_VALUE + "&max=" + MAX_VALUE +
                    "&col=1&base=10&format=plain&rnd=new");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            for (int i = 0; i < NUM_DIGITS; i++) {
                code[i] = Integer.parseInt(reader.readLine());
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
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
