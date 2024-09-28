package com.company;

public class Main {
    public static void main (String[] args) {
        int n = 5;
        int numPlayers = 3;
        long timeLimit = 10000;
        Gameplay game = new Gameplay(n, numPlayers);
        game.start(timeLimit);
    }
}