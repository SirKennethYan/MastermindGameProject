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
    public void testGeneratedSecretCode() {
        int[] secretCode = code.generateSecretCode();
        assertEquals(MastermindGame.NUM_DIGITS, secretCode.length);

        for (int value : secretCode) {
            assertTrue(value >= MastermindGame.MIN_VALUE && value <= MastermindGame.MAX_VALUE);
        }
    }

}
