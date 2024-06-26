import java.io.*;
import java.net.*;

class Server {
    private Socket strategy1Socket;
    private int strategy1Score;
    private Socket strategy2Socket;
    private int strategy2Score;
    private ServerSocket serverSocket;

    private int port;
    private int totalRounds;

    Server(int port, int totalRounds) {
        this.port = port;
        this.totalRounds = totalRounds;
    }

    Server() {
        this(5000, 200);
    }

    void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            strategy1Socket = serverSocket.accept();
            System.out.println("Strategy 1 connected");
            strategy2Socket = serverSocket.accept();
            System.out.println("Strategy 2 connected");
        } catch (IOException e) {
            System.out.println(e + " Could not connect...\nExiting...");
            System.exit(0);
        }
    }

    void calculateScore(String strategy1Move, String strategy2Move) {
        if (strategy1Move.equals("c") && strategy2Move.equals("d")) {
            strategy2Score += 5;
        } else if (strategy1Move.equals("d") && strategy2Move.equals("c")) {
            strategy1Score += 5;
        } else if (strategy1Move.equals("c") && strategy2Move.equals("c")) {
            strategy1Score += 3;
            strategy2Score += 3;
        } else if (strategy1Move.equals("d") && strategy2Move.equals("d")) {
            strategy1Score += 1;
            strategy2Score += 1;
        }
    }

    void startGamePlay() {
        Messenger strategy1Messenger = new Messenger(strategy1Socket);
        Messenger strategy2Messenger = new Messenger(strategy2Socket);

        strategy1Messenger.sendMessage(Integer.toString(totalRounds));
        strategy2Messenger.sendMessage(Integer.toString(totalRounds));

        while (totalRounds > 0) {
            String strategy1Move = strategy1Messenger.getMessage();
            String strategy2Move = strategy2Messenger.getMessage();

            System.out.println("Strategy 1: " + strategy1Move);
            System.out.println("Strategy 2: " + strategy2Move + "\n");

            calculateScore(strategy1Move, strategy2Move);

            strategy1Messenger.sendMessage(strategy2Move);
            strategy2Messenger.sendMessage(strategy1Move);

            totalRounds--;
        }

        System.out.println("Final Scores:");
        System.out.println("Strategy 1: " + strategy1Score);
        System.out.println("Strategy 2: " + strategy2Score);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
        server.startGamePlay();
    }
}

class Messenger extends Thread {
    private Socket strategySocket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    Messenger(Socket strategySocket) {
        this.strategySocket = strategySocket;
        try {
            inputReader = new BufferedReader(new InputStreamReader(strategySocket.getInputStream()));
            outputWriter = new PrintWriter(strategySocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getMessage() {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    void sendMessage(String message) {
        outputWriter.println(message);
    }
}
