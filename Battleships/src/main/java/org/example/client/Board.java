package org.example.client;

public class Board {
    private char[][] grid;

    public Board() {
        grid = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void placeShip(int x, int y) {
        grid[x][y] = 'S';
    }

    public boolean attack(int x, int y) {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X';
            return true;
        } else {
            grid[x][y] = 'O';
            return false;
        }
    }

    public boolean allShipsDestroyed() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 'S') {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
