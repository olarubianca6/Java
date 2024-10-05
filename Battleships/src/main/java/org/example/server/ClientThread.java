package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String request;
            while ((request = in.readLine()) != null) {
                if (request.equals("stop")) {
                    out.println("Server stopped");
                    server.stop();
                    break;
                } else {
                    out.println("Server received the request: " + request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
            server.removeClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
