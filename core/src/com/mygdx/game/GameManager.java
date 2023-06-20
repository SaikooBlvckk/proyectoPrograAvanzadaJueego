package com.mygdx.game;

public class GameManager {
    private static GameManager instance;
    private int score;
    private int lives;
    private int round;

    private GameManager() {
        score = 0;
        lives = 3;
        round = 1;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // Métodos para manipular el puntaje y las vidas
    public int getScore() {
        return score;
    }

    public void increaseScore(int increment) {
        score += increment;
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLives() {
        lives--;
    }

    // Métodos para manejar rondas
    public int getRound() {
        return round;
    }

    public void nextRound() {
        round++;
    }
}


