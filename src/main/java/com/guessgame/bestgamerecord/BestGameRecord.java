package com.guessgame.bestgamerecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BestGameRecord {
    private String difficulty;
    private int attempts;
    private Date date; // Usamos java.util.Date para guardar la fecha de la partida
    private static List<BestGameRecord> bestGames = new ArrayList<>();
    private static final int MAX_BEST_GAMES_TO_KEEP = 5;

    public BestGameRecord(String difficulty, int attempts, Date date) {
        this.difficulty = difficulty;
        this.attempts = attempts;
        this.date = date;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static int getMaxBestGamesToKeep() {
        return MAX_BEST_GAMES_TO_KEEP;
    }

    @Override
    public String toString() {
        return "Fecha: " + date + ". Dificultad: " + difficulty + ", intentos: " + attempts + ".";
    }

    public static List<BestGameRecord> getBestGames() {
        return bestGames;
    }
}