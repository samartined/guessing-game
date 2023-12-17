package com.guessgame.logicgame;

public class GuessNumberGame {
    private int numberToGuess;
    private int attempts;
    private int maxAttempts;

    public GuessNumberGame(int maxAttempts) {
        this.numberToGuess = generateRandomNumber();
        this.attempts = 0;
        this.maxAttempts = maxAttempts;
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }

    public void incrementAttempts() {
        attempts++;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public void setNumberToGuess(int numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean checkGuess(int userGuess) {
        return userGuess == numberToGuess;
    }

    public boolean isGameOver() {
        return attempts >= maxAttempts;
    }
}
