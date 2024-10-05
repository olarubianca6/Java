package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String address, int port) throws IOException {
        socket = new Socket(address, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() throws IOException {
        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            System.out.println("Connected to the server. Type 'exit' to quit.");
            while (true) {
                System.out.print("Enter command: ");
                input = keyboard.readLine();
                if (input.equals("exit")) {
                    break;
                }
                out.println(input);
                String response = in.readLine();
                System.out.println("Response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() throws IOException {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            GameClient client = new GameClient("localhost", 5000);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
