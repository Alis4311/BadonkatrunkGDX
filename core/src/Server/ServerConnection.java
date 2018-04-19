package Server;


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
 *
 * @author Peder Nilsson & xxxx & xxxxx & xxxxx
 *
 * Bara ett utkast -  fixa och trixa - allihopa  - go!!
 */

public class ServerConnection implements Runnable {


    private Thread server = new Thread(this);
    private ServerSocket serverSocket;


    public ServerConnection(int port) throws IOException {
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


        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            System.out.println("Client connected");
            try {ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    int track = dis.readInt();
                    long trackTime = dis.readLong();
                    //response = ServerLeaderboard.compareTime(track, trackTime);
                   // dos.writeUTF(response);
                    dos.flush();
                    dos.close();
                    dis.close();
                }
            } catch (IOException e) {
            }
            try {
                socket.close();
            } catch (Exception e) {
            }
            System.out.println("Client disconnected");
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerConnection(3464);
    }
}


