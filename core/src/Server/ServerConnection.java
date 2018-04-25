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
 * Has an inner class ClientHandler that gives the client its socket trasports
 * user track info to and from methods in the class ServerLeaderboard.
 *
 * @author Peder Nilsson & xxxx & xxxxx & xxxxx
 * <p>
 * Bara ett utkast -  fixa och trixa - allihopa  - go!!
 */

public class ServerConnection implements Runnable {


    private Thread server = new Thread(this);
    private ServerSocket serverSocket;
    private ServerLeaderboard leaderboard;


    public ServerConnection(int port) throws IOException {
        leaderboard = new ServerLeaderboard();
        serverSocket = new ServerSocket(port);
        server.start();
    }


    public void run() {
        System.out.println("Server running");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private HighScore score;
        private boolean onLeaderboard;
        private ArrayList<HighScore> newLeaderboard;


        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            System.out.println("Client connected");
            try {
                ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
                score = (HighScore) dis.readObject();
                newLeaderboard = leaderboard.checkHighScore(score);
                onLeaderboard = leaderboard.isOnLeaderboard();
                dos.writeBoolean(onLeaderboard);
                dos.writeObject(newLeaderboard);
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

        public ArrayList<HighScore> getNewLeaderboard() {
            return newLeaderboard;
        }

        public boolean isOnLeaderboard() {
            return onLeaderboard;
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerConnection(3464);
    }
}


