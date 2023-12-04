package test.java.entities;

import main.java.entities.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class PlayerTest {

    @Test
    public void testGetPlayerGuessValidInput() {
        // Arrange the necessary parameters.
        int numDigits = 4;
        int minValue = 1;
        int maxValue = 8;

        // Set up input stream with mocked user input.
        String simulatedInput = "1 2 3 4";
        InputStream mockedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        // The getPlayerGuess method is called within a try block. The
        // System.setIn is used to set the standard input stream to
        // the mocked input stream. The result of the method call is stored in the
        // playerGuess array.
        try (Scanner scanner = new Scanner(mockedInputStream)) {
            System.setIn(mockedInputStream);

            int[] playerGuess = Player.getPlayerGuess(numDigits, minValue, maxValue, false);

            assertArrayEquals(new int[] { 1, 2, 3, 4 }, playerGuess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
