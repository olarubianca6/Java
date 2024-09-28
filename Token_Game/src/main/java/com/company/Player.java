package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {
    private final String name;
    private final Bag bag;
    private List<Token> sequence;
    private static final Object turnlock = new Object();
    private static boolean gameWon = false;
    private final int n;
    private int score;

    public Player(String name, Bag bag, int n) {
        this.name = name;
        this.bag = bag;
        this.n = n;
        this.sequence = new ArrayList<>();
        this.score = 0;
    }

    @Override
    public void run() {
        while (!bag.isEmpty() && !gameWon) {
            synchronized (turnlock) {
                Token token = bag.extractToken();
                if (token != null) {
                    sequence.add(token);
                    System.out.println(name + " extracted " + token);
                    checkSequence();
                }

                turnlock.notifyAll();
                try {
                    turnlock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        score = sequence.size();
        System.out.println(name + " finished with a score of " + score);
    }

    private void checkSequence() {
        if (sequence.size() == n) {
            System.out.println(name + " formed a sequence of size " + n + " and wins the game!");
            gameWon = true;
        }
    }

    public int getScore() {
        return score;
    }
}
