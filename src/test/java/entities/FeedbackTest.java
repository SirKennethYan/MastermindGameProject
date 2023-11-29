package test.java.entities;

import main.java.app.MastermindGame;
import main.java.entities.Feedback;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;

public class FeedbackTest {
    private MastermindGame game;

    @BeforeEach
    public void setUp() {
        game = new MastermindGame();
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
