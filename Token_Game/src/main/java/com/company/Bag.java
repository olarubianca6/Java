package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Bag {
    private List<Token> tokens;

    public Bag(int n) {
        tokens = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                tokens.add(new Token(i, j));
            }
        }
        Collections.shuffle(tokens);
    }

    public synchronized Token extractToken() {
        if (tokens.isEmpty()) {
            return null;
        }
        return tokens.removeFirst();
    }

    public synchronized boolean isEmpty() {
        return tokens.isEmpty();
    }
}
