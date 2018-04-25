package ClientHighScore;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


/**
 * Handels a connection to Server sending and recieving information to and
 * from ServerLeaderboard via Serverconnection.
 *
 * @author Peder Nilsson and xx and xx
 */


public class ClientConnection extends Thread {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private String ip;
    private int port;
    private HighScore score;
    private boolean onLeaderboard;
    private ArrayList<HighScore> newLeaderboard = new ArrayList<HighScore>();


    public ClientConnection(String ip, int port, HighScore score){
        this.ip = ip;
        this.port = port;
        this.score = score;
        start();
    }

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
        }
    }
}
