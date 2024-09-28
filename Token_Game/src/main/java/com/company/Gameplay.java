package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Gameplay {
    private final int n;
    private final int numPlayers;
    private Bag bag;
    private final ArrayList<Player> players;

    public Gameplay (int n, int numPlayers) {
        this.n = n;
        this.numPlayers = numPlayers;
        this.bag = new Bag(n);
        this.players = new ArrayList<>();
        createPlayers();
    }

    private void createPlayers() {
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i, bag, n));
        }
    }

    public void start(long timeLimit) {
        Timekeeper timekeeper = new Timekeeper(timeLimit);
        timekeeper.start();

        for (Player player : players) {
            player.start();
        }

        for (Player player : players) {
            try {
                player.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Player winner = players.stream().max((p1, p2) -> Integer.compare(p1.getScore(), p2.getScore())).orElse(null);
        if (winner != null) {
            System.out.println("The winner is " + winner.getName() + " with a score of " + winner.getScore());
        }
    }
}
