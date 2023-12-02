package test.java.resources;

import org.junit.jupiter.api.Test;

import main.java.resources.GameInstructions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameInstructionsTest {

    @Test
    public void testPrintInstructions() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        GameInstructions.printInstructions();

        // Assert
        String consoleOutput = outputStream.toString().trim();

        // Print debug
        System.out.println("Console Output:\n" + consoleOutput);

        // Check if the console output is not empty
        assertTrue(!consoleOutput.isEmpty());

        // Reset System.out to the original PrintStream
        System.setOut(System.out);
    }
}
