package com.emi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WordsClient {

    static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 9000;
    private Socket clientSocket;
    private BufferedReader incomingMessages;
    private PrintWriter outputMessages;

    public static void main(String[] args) {
        try {
            new WordsClient().startClient();
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }

    public void startClient() throws IOException {
        clientSocket = new Socket(ADDRESS, PORT);
        incomingMessages = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outputMessages = new PrintWriter(clientSocket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);
        String sentMessage = "";

        while (!"stop".equalsIgnoreCase(sentMessage)) {
            System.out.println("Enter your message: ");
            sentMessage = scanner.nextLine();
            System.out.println("You sent to the server: " + sentMessage);
            outputMessages.println(sentMessage);
            String responseFromServer = incomingMessages.readLine();
            System.out.println("Message from the server: " + responseFromServer);
        }

        incomingMessages.close();
        outputMessages.close();
        clientSocket.close();

    }

}
