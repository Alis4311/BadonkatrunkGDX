package ClientHighScore;

import Screens.HighScoreScreen;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


/**
 * Handels a connection to Server sending and receiving information to and
 * from ServerLeaderboard via Serverconnection.
 *
 * @author Markus Wendler & Peder Nilsson
 */


public class ClientConnection extends Thread {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private String ip;
    private int port;
    private HighScore score;
    private HighScoreScreen screen;
    private boolean onLeaderboard = false;
    private ArrayList<HighScore> newLeaderboard = new ArrayList<HighScore>();


    public ClientConnection(String ip, int port, HighScore score, HighScoreScreen screen){
        this.ip = ip;
        this.port = port;
        this.score = score;
        this.screen = screen;
        start();
    }

    /**
     * Run method that opens a connection to the server and sends the high score achieved on the current map.
     */
    public void run() {
        try {
            socket = new Socket(ip, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(score);
            oos.flush();
            onLeaderboard = ois.readBoolean();
            newLeaderboard = (ArrayList<HighScore>) ois.readObject();

            oos.close();
            ois.close();


        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException ce){
            ce.printStackTrace();
        } finally {
            screen.isOnLeaderboard(onLeaderboard);
            screen.showLeaderboard(newLeaderboard);
        }
    }

}
