package com.company;

public class Timekeeper extends Thread{
    private final long timeLimit;

    public Timekeeper(long timeLimit) {
        this.timeLimit = timeLimit;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeLimit);
            System.out.println("Time's up! Game over.");
            System.exit(0);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
