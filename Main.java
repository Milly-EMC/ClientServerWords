package com.emi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(9000)){
            System.out.println("Server is waiting for messages");

            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected " + clientSocket.getInetAddress() + " / " + clientSocket.getPort());

                Thread client = new ClientHandlerWords(clientSocket);
                client.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
