package server_connection_files;

import java.io.*;
import java.net.*;

public class ServerConnectionJava {
    private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private int totalRounds;

    // Constructor to establish connection
    public ServerConnectionJava() {
        try {
            socket = new Socket("localhost", 5000);
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputWriter = new PrintWriter(socket.getOutputStream(), true);
            totalRounds = Integer.parseInt(inputReader.readLine()); // Assuming the server sends the total rounds first
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the total number of rounds
    public int getTotalRounds() {
        return totalRounds;
    }

    // Method to send the move to the server
    public void tellMove(String move) {
        outputWriter.println(move);
    }

    // Method to receive the opponent's move from the server
    public String knowMove() {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Close the connection
    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
