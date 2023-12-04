package test.java.app;

import org.junit.jupiter.api.BeforeEach;
import main.java.app.MastermindGame;

public class MastermindGameTest {
    public static void main(String[] args) {
        MastermindGameTest test = new MastermindGameTest();
        test.setUp();
    }

    @BeforeEach
    void setUp() {
        new MastermindGame();

    }

}
