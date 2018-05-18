package Server;


import ClientHighScore.HighScore;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Sets up the connection between server and client.
 * Has an inner class ClientHandler that gives the client its socket. Receives and sends information.
 *
 * @author Peder Nilsson & Markus Wendler
 *
 */

class ServerConnection implements Runnable {


    private ServerSocket serverSocket;
    private ServerLeaderboard leaderboard;


    private ServerConnection(int port) throws IOException {
        leaderboard = new ServerLeaderboard();
        serverSocket = new ServerSocket(port);
        Thread server = new Thread(this);
        server.start();
    }


    /**
     * Run method that accepts clients and assigns a ClientHandler and a thread.
     */
    public void run() {
        System.out.println("Server running");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A class that handles every client that connects.
     */
    private class ClientHandler extends Thread {
        private Socket socket;
        private HighScore score;
        private boolean onLeaderboard;
        private ArrayList<HighScore> newLeaderboard;


        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Run method that establishes a connection and then receives a high score and handles that high score.
         */
        public void run() {
            System.out.println("Client connected");
            try {
                ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
                score = (HighScore) dis.readObject();
                System.out.println("Score received");
                newLeaderboard = leaderboard.checkHighScore(score);
                System.out.println("Leaderboard created");
                onLeaderboard = leaderboard.isOnLeaderboard();
                System.out.println("On Leaderboard: " + onLeaderboard);
                dos.writeBoolean(onLeaderboard);
                System.out.println("sent onLeaderboard");
                dos.writeObject(newLeaderboard);
                System.out.println("sent newLeaderboard");
                dos.flush();
                dos.close();
                dis.close();

            } catch (IOException e) {
            } catch (ClassNotFoundException ce) {

            }
            try {
                socket.close();
            } catch (Exception e) {
            }
            System.out.println("Client disconnected");
        }

        /**
         * Returns the ArrayList with all the high scores.
         * @return - ArrayList<HighScore>
         */
        public ArrayList<HighScore> getNewLeaderboard() {
            return newLeaderboard;
        }

        /**
         * Returns true if the received high score is on the leaderboard.
         * @return
         */
        public boolean isOnLeaderboard() {
            return onLeaderboard;
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerConnection(8080);
    }
}


