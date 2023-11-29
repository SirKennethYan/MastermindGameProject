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
        // Arrange
        int numDigits = 4;
        int minValue = 1;
        int maxValue = 8;

        // Set up input stream with mocked user input
        String simulatedInput = "1 2 3 4";
        InputStream mockedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        // Act
        try (Scanner scanner = new Scanner(mockedInputStream)) {
            System.setIn(mockedInputStream);

            int[] playerGuess = Player.getPlayerGuess(numDigits, minValue, maxValue);

            // Assert
            assertArrayEquals(new int[] { 1, 2, 3, 4 }, playerGuess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
