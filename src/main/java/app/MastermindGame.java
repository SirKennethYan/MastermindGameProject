package main.java.app;

import java.util.Scanner;
import main.java.entities.CodeGenerator;
import main.java.ui.DifficultyLevel;
import main.java.ui.MenuHandler;
import main.java.entities.GameState;

public class MastermindGame {
    // Default case (MEDIUM) no need to change the values
    public static int NUM_ATTEMPTS = 10;
    public static final int NUM_DIGITS = 4;
    public static int MIN_VALUE = 1;
    public static int MAX_VALUE = 8;

    private DifficultyLevel difficultyLevel;
    private CodeGenerator codeGenerator;
    private GameState gameState;
    public int[] secretCode;
    public int[] playerGuess;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MastermindGame game = new MastermindGame();
            GameController gameController = new GameController(game.gameState, scanner); // Pass game.gameState
            System.out.println("Welcome to Mastermind!");

            do {
                MenuHandler.printMenu(game.gameState, gameController); // Pass game.gameState
                gameController.playGame();

                if (!gameController.askToPlayAgain()) {
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                }

                System.out.println("\nNew Game!\n");
                game.gameState.resetGame(); // Use game.gameState

            } while (true);
        }
    }

    public MastermindGame() {
        codeGenerator = new CodeGenerator();
        secretCode = codeGenerator.generateSecretCode();
        playerGuess = new int[NUM_DIGITS];
        gameState = new GameState();
        difficultyLevel = DifficultyLevel.MEDIUM;
        gameState.setDifficulty(difficultyLevel); // Set difficulty in GameState
        gameState.resetGame();
    }

}
