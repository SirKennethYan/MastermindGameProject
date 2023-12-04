package test.java.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.app.MastermindGame;
import main.java.entities.CodeGenerator;

public class CodeGeneratorTest {
    private CodeGenerator code;

    @BeforeEach
    public void setUp() {
        code = new CodeGenerator();
    }

    @Test
    // Calls the generateSecretCode method to obtain an array representing the
    // secret code.
    public void testGeneratedSecretCode() {
        int[] secretCode = code.generateSecretCode();
        // Checks if the length of the generated secret code array matches the expected
        // length defined by NUM_DIGITS.
        assertEquals(MastermindGame.NUM_DIGITS, secretCode.length);
        // Iterates through each value in the generated secret code array, ensuring that
        // values falls within the specified range defined by
        // MIN_VALUE and MAX_VALUE.
        for (int value : secretCode) {
            assertTrue(value >= MastermindGame.MIN_VALUE && value <= MastermindGame.MAX_VALUE);
        }
    }

}
