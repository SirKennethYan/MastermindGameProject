package test.java.resources;

import org.junit.jupiter.api.Test;

import main.java.resources.GameInstructions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameInstructionsTest {

    @Test
    public void testPrintInstructions() {
        // Creates a new ByteArrayOutputStream to capture the output intended for the
        // console.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Calls the printInstructions method of the GameInstructions class, which
        // prints instructions to the console.
        GameInstructions.printInstructions();

        // Converts the captured output from the ByteArrayOutputStream to a string and
        // trims any leading or trailing whitespaces.
        String consoleOutput = outputStream.toString().trim();

        // Print debug
        System.out.println("Console Output:\n" + consoleOutput);

        // Check if the console output is not empty
        assertTrue(!consoleOutput.isEmpty());

        // Resets the standard output to the original PrintStream to prevent
        // interference with other tests or subsequent code.
        System.setOut(System.out);
    }
}
