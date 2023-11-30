package main.java.entities;

import java.util.Arrays;

public class GameState {
    private int[] secretCode;
    private int[] playerGuess;
    private int attemptsLeft;
    private boolean gameEnded;

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

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    @Override
    public String toString() {
        return "GameState [secretCode=" + Arrays.toString(secretCode) + ", playerGuess=" + Arrays.toString(playerGuess)
                + ", attemptsLeft=" + attemptsLeft + ", gameEnded=" + gameEnded + "]";
    }

}
