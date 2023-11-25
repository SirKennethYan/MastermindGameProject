package test;

import org.junit.jupiter.api.BeforeEach;
import main.java.app.MastermindGame;
import main.java.entities.CodeGenerator;
import main.java.entities.Feedback;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MastermindGameTest {
    private MastermindGame game;
    private CodeGenerator codeGenerator;

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
        codeGenerator = new CodeGenerator();
        new Feedback();
    }

    @Test
    public void testGeneratedSecretCode() {
        int[] secretCode = codeGenerator.generateSecretCode();
        assertEquals(MastermindGame.NUM_DIGITS, secretCode.length);

        for (int value : secretCode) {
            assertTrue(value >= MastermindGame.MIN_VALUE && value <= MastermindGame.MAX_VALUE);
        }
    }

    @Test
    public void testPlayerGuessFeedback() {
        // All Correct Values
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 2, 3, 4 };
        int[] feedbackResult = Feedback.getFeedback(game.secretCode, game.playerGuess);
        assertArrayEquals(new int[] { 0, 4 }, feedbackResult);

        // 1 Correct Value, Correct Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 5, 6, 7 };
        feedbackResult = Feedback.getFeedback(game.secretCode, game.playerGuess);
        assertArrayEquals(new int[] { 0, 1 }, feedbackResult);

        // 2 Correct Values, Correct Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 2, 5, 6 };
        feedbackResult = Feedback.getFeedback(game.secretCode, game.playerGuess);
        assertArrayEquals(new int[] { 0, 2 }, feedbackResult);

        // 3 Correct Values, Correct Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 1, 2, 3, 5 };
        feedbackResult = Feedback.getFeedback(game.secretCode, game.playerGuess);
        assertArrayEquals(new int[] { 0, 3 }, feedbackResult);

        // 4 Correct Values, Wrong Space
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 4, 3, 2, 1 };
        feedbackResult = Feedback.getFeedback(game.secretCode, game.playerGuess);
        assertArrayEquals(new int[] { 4, 0 }, feedbackResult);

        // No Correct Values
        game.secretCode = new int[] { 1, 2, 3, 4 };
        game.playerGuess = new int[] { 5, 6, 7, 8 };
        feedbackResult = Feedback.getFeedback(game.secretCode, game.playerGuess);
        assertArrayEquals(new int[] { 0, 0 }, feedbackResult);
    }

}
