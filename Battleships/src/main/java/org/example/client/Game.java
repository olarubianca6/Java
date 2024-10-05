package org.example.client;

import org.example.server.ClientThread;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<ClientThread, Player> players;
    private Board board1;
    private Board board2;
    private boolean gameStarted;
    private boolean player1Turn;

    public Game() {
        players = new HashMap<>();
        board1 = new Board();
        board2 = new Board();
        gameStarted = false;
        player1Turn = true;
    }

    public void addPlayer(ClientThread client, Player player) {
        if (players.size() < 2) {
            players.put(client, player);
            if (players.size() == 2) {
                gameStarted = true;
                players.values().iterator().next().setTurn(true);
                System.out.println("Game starting..");
            }
        } else {
            System.out.println("Game is full.");
        }
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    private boolean isGameOver() {
        if (board1.allShipsDestroyed() || board2.allShipsDestroyed()) {
            gameStarted = false;
            return true;
        }
        return false;
    }


    public synchronized String submitMove(ClientThread client, int x, int y) {
        Player currentPlayer = players.get(client);

        if (currentPlayer == null) {
            return "Error. Player not in game.";
        }
        if (!currentPlayer.isTurn()) {
            return "Error. It's not your turn.";
        }

        boolean hit;
        if (currentPlayer == players.values().toArray()[0]) {
            hit = board2.attack(x, y);
        } else {
            hit = board1.attack(x, y);
        }

        if (isGameOver()) {
            return currentPlayer.getName() + " wins!";
        }

        switchTurns();

        return hit ? "Hit!" : "Miss!";
    }

    private void switchTurns() {
        for (Player player : players.values()) {
            player.setTurn(!player.isTurn());
        }
        player1Turn = !player1Turn;
    }

    public Board getBoard(ClientThread client) {
        Player currentPlayer = players.get(client);
        if (currentPlayer == players.values().toArray()[0]) {
            return board1;
        } else {
            return board2;
        }
    }
}
