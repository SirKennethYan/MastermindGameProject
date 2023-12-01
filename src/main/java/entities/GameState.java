package main.java.entities;

import java.util.Arrays;
import main.java.ui.DifficultyLevel;

public class GameState {
    public static int NUM_ATTEMPTS = 10;
    public static final int NUM_DIGITS = 4;
    public static int MIN_VALUE = 1;
    public static int MAX_VALUE = 8;

    private int[] secretCode;
    private int[] playerGuess;
    private int attemptsLeft;
    private boolean gameEnded;
    private DifficultyLevel difficultyLevel;
    private boolean twoPlayerMode;
    private CodeGenerator codeGenerator;

    public void resetGame() {
        if (!twoPlayerMode) {
            switch (difficultyLevel) {
                case EASY:
                    NUM_ATTEMPTS = 12;
                    MIN_VALUE = 1;
                    MAX_VALUE = 6;
                    break;
                case MEDIUM: // Default
                    NUM_ATTEMPTS = 10;
                    MIN_VALUE = 1;
                    MAX_VALUE = 8;
                    break;
                case HARD:
                    NUM_ATTEMPTS = 8;
                    MIN_VALUE = 1;
                    MAX_VALUE = 10;
                    break;
            }

            codeGenerator = new CodeGenerator();
            secretCode = codeGenerator.generateSecretCode();
            attemptsLeft = NUM_ATTEMPTS;

        }

        playerGuess = new int[NUM_DIGITS];
    }

    public int getNumDigits() {
        return NUM_DIGITS;
    }

    public int getMinValue() {
        return MIN_VALUE;
    }

    public int getMaxValue() {
        return MAX_VALUE;
    }

    public int[] getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(int[] secretCode) {
        this.secretCode = secretCode;
    }

    public int[] getPlayerGuess() {
        return playerGuess;
    }

    public void setPlayerGuess(int[] playerGuess) {
        this.playerGuess = playerGuess;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficultyLevel = difficulty;
    }

    public void setTwoPlayerMode(boolean twoPlayerMode) {
        this.twoPlayerMode = twoPlayerMode;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public boolean isTwoPlayerMode() {
        return twoPlayerMode;
    }

    public void decrementAttemptsLeft() {
        attemptsLeft--;
    }

    @Override
    public String toString() {
        return "GameState [secretCode=" + Arrays.toString(secretCode) + ", playerGuess=" + Arrays.toString(playerGuess)
                + ", attemptsLeft=" + attemptsLeft + ", gameEnded=" + gameEnded + ", difficultyLevel=" + difficultyLevel
                + ", twoPlayerMode=" + twoPlayerMode + ", codeGenerator=" + codeGenerator + "]";
    }

}
