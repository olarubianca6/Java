package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private ServerSocket serverSocket;
    private boolean running;
    private List<ClientThread> clients;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        clients = new ArrayList<>();
        System.out.println("Server started on port " + port);
    }

    public void start() {
        try {
            while (running) {
                System.out.println("Waiting for clients to connect...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    public void stop() {
        running = false;
        try {
            for (ClientThread client: clients) {
                client.close();
            }
            serverSocket.close();
            System.out.println("Server stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClient(ClientThread client) {
        clients.remove(client);
    }

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer(8080);
        server.start();
    }
}
