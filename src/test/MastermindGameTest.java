package test;

import org.junit.jupiter.api.BeforeEach;
import main.java.app.MastermindGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MastermindGameTest {
    private MastermindGame game;

    public static void main(String[] args) {
        MastermindGameTest test = new MastermindGameTest();
        test.setUp();
        test.testGeneratedSecretCode();
        test.testPlayerGuessFeedback();
        // test.testGameOutcome();
    }

    @BeforeEach
    void setUp() {
        game = new MastermindGame();
    }

    @Test
    public void testGeneratedSecretCode() {
        int[] secretCode = game.generateSecretCode();
        assertEquals(MastermindGame.NUM_DIGITS, secretCode.length);

        for (int value : secretCode) {
            assertTrue(value >= MastermindGame.MIN_VALUE && value <= MastermindGame.MAX_VALUE);
        }
    }

    @Test
    public void testPlayerGuessFeedback() {
        // All Correct Valules
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 2, 3, 4 };
        int[] feedback = game.getFeedback();
        assertArrayEquals(new int[] { 0, 4 }, feedback);

        // 1 Correct Valules, Correct Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 5, 6, 7 };
        feedback = game.getFeedback();
        assertArrayEquals(new int[] { 0, 1 }, feedback);

        // 2 Correct Valules, Correct Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 2, 5, 6 };
        feedback = game.getFeedback();
        assertArrayEquals(new int[] { 0, 2 }, feedback);

        // 3 Correct Valules, Correct Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 2, 3, 5 };
        feedback = game.getFeedback();
        assertArrayEquals(new int[] { 0, 3 }, feedback);

        // 4 Correct Valules, Wrong Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 4, 3, 2, 1 };
        feedback = game.getFeedback();
        assertArrayEquals(new int[] { 4, 0 }, feedback);

        // No Correct Valules
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 5, 6, 7, 8 };
        feedback = game.getFeedback();
        assertArrayEquals(new int[] { 0, 0 }, feedback);

    }

    // @Test
    // @Timeout(value = 20, unit = TimeUnit.SECONDS)
    // public void testGameOutcome() {
    // // Any Valid Guess Scenario #1
    // game.secretCode = new int[] { 1, 2, 3, 4 };
    // game.playerGuess = new int[] { 1, 2, 3, 5 };
    // int initialAttemptsValidGuess = game.attemptsLeft;
    // // Expect the attemptsLeft to decrease by 1
    // game.playGame();
    // assertEquals(initialAttemptsValidGuess - 1, game.attemptsLeft);

    // // Any Valid Guess Scenario #2
    // game.secretCode = new int[] { 1, 2, 3, 4 };
    // game.playerGuess = new int[] { 1, 2, 4, 3 };
    // int initialAttemptsZeroGuess = game.attemptsLeft;
    // // Play the game until attemptsLeft is 0
    // for (int i = 0; i < initialAttemptsZeroGuess; i++) {
    // game.playGame();
    // }
    // // After 10 rounds, expect the attemptsLeft to be 0
    // assertEquals(0, game.attemptsLeft);
    // // Try playing the game again, attemptsLeft should still be 0
    // game.playGame();
    // assertEquals(0, game.attemptsLeft);
    // }

}
