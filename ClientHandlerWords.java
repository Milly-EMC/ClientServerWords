package com.emi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ClientHandlerWords extends Thread {
    private final Socket socket;
    private static List<String> receivedWords = new ArrayList<>();

    public ClientHandlerWords(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedReader incomingMessages = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter outputMessages = new PrintWriter(socket.getOutputStream(), true)) {

            String receivedMessage;

            while (true) {
                try {
                    receivedMessage = incomingMessages.readLine();

                    if ("stop".equalsIgnoreCase(receivedMessage)) {
                        System.out.println("Client disconnected!");
                        outputMessages.println("stop");
                        break;
                    }

                    if (!wordExists(receivedMessage)) {
                        System.out.println("Word \"" + receivedMessage + "\" was added successfully");
                        receivedWords.add(receivedMessage);
                        outputMessages.println("Added to the list");
                    } else {
                        System.out.println("Word \"" + receivedMessage + "\" already exists.");
                        outputMessages.println("Word is already in the list");
                    }

                } catch (IOException e) {
                    e.getMessage();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean wordExists(String newWord) {
        for (String word : receivedWords) {
            if (newWord.equals(word))
                return true;
        }

        return false;
    }
}
