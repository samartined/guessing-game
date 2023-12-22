package com.guessgame.logicgame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.guessgame.bestgamerecord.BestGameRecord;

public class GuessNumberGame {
    private int numberToGuess;
    private int attempts;
    private int maxAttempts;
    private String difficulty;
    private List<BestGameRecord> bestGamesHistory = new ArrayList<>();

    public GuessNumberGame(int maxAttempts, String difficulty) {
        this.numberToGuess = generateRandomNumber();
        this.attempts = 0;
        this.maxAttempts = maxAttempts;
        this.difficulty = difficulty;
        this.bestGamesHistory = new ArrayList<>();
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

    public void gameCompletedSuccessfully() {
        int maxBestGamesToKeep = BestGameRecord.getMaxBestGamesToKeep();

        if (bestGamesHistory.size() < maxBestGamesToKeep) {
            // Agrega el registro a la lista de mejores partidas si no se alcanzó el límite
            BestGameRecord record = new BestGameRecord(difficulty, attempts, new Date());
            bestGamesHistory.add(record);
        } else {
            // Encuentra la partida con el mayor número de intentos y compárala con la
            // partida actual
            BestGameRecord maxAttemptsRecord = bestGamesHistory.stream()
                    .max(Comparator.comparingInt(BestGameRecord::getAttempts))
                    .orElse(null);

            if (maxAttemptsRecord != null && attempts < maxAttemptsRecord.getAttempts()) {
                // Reemplaza la partida con el mayor número de intentos con la actual
                bestGamesHistory.remove(maxAttemptsRecord);
                BestGameRecord record = new BestGameRecord(difficulty, attempts, new Date());
                bestGamesHistory.add(record);
            }
        }
    }
}
