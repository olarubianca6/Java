package org.example.client;

public class Player {
    private String name;
    private boolean isTurn;

    public Player(String name) {
        this.name = name;
        this.isTurn = false;
    }

    public String getName() {
        return name;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }
}

