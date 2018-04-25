package ClientHighScore;

import java.io.*;
import java.net.*;




/**
 * Handels a connection to Server sending and recieving information to and
 * from ServerLeaderboard via Serverconnection.
 *
 * @author Peder Nilsson and xx and xx
 */


    public class ClientConnection {
        private Thread connectionThread;
        private Thread sendHighscoreRequest;
        private Thread recieveHighscoreResult;


        private String serverIP;
        private int serverPort;

        /**
         * Handles connection to a server,sends an Highscore object for comparing when a track is finished
         * and recieves an updated leaderboard with user results
         *
         * @param serverIP
         * @param serverPort

         */
        public ClientConnection(String serverIP, int serverPort) {
            this.serverIP = serverIP;
            this.serverPort = serverPort;

        }

        /**
         * Connects to the server
         * will start in a new thread
         */
        public void connect() {
            if (connectionThread != null && !connectionThread.isInterrupted()) return;
            connectionThread = new Thread(() -> startConnection());
            connectionThread.start();
        }

        /**
         * Close connection to server
         * Will stop all running threads gracefully
         */
        public void close() {
            if (connectionThread == null || connectionThread.isInterrupted()) return;
            connectionThread.interrupt();
            //incomingMessagesThread.interrupt();
            //outgoingMessagesThread.interrupt();
            //incomingMessagesThread = null;
            //outgoingMessagesThread = null;
            connectionThread = null;
        }

        /**
         * Starts the connection to server
         * Will start 2 threads:
         * - one to receive incoming messages
         * - one to send outgoing messages
         */

        private void startConnection() {
            Socket socket;
            ObjectInputStream inputStream;
            ObjectOutputStream outputStream;

            try {
                socket = new Socket(serverIP, serverPort);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("Connection to server established.");
            } catch (IOException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return;
            }

           // incomingMessagesThread = new Thread(() -> handleIncomingMessages(inputStream));
            //outgoingMessagesThread = new Thread(() -> handleOutgoingMessages(outputStream));

            //incomingMessagesThread.start();
            //outgoingMessagesThread.start();

            // Wait for threads to complete/die
            try {
                //incomingMessagesThread.join();
                //outgoingMessagesThread.join();
            } catch (InterruptedException e) {}

            try {
                inputStream.close();
            } catch (IOException e) {} finally {
                try {
                    outputStream.close();
                } catch (IOException e) {} finally {
                    try {
                        socket.close();
                    } catch (IOException e) {}
                }
            }
            System.out.println("Connection to server closed.");
        }

        /**
         * Takes messages from the provided ObjectInputStream and puts them into the incomingMessageQueue
         *
         * @param inputStream ObjectInputStream from the connected server
         */
        private void handleIncomingMessages(ObjectInputStream inputStream) {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                   // Message nextMessage = (Message) inputStream.readObject();
                    System.out.println("RECEIVED MSG: " + nextMessage);
                   // incomingMessageQueue.put(nextMessage);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        /**
         * Takes messages from the outgoingMessageQueue and send them to the connected server
         *
         * @param outputStream ObjectOutputStream from the connected server
         */
        private void handleOutgoingMessages(ObjectOutputStream outputStream) {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    //Message nextMessage = outgoingMessageQueue.get();
                   // System.out.println("SENDING MSG: " + nextMessage);
                   // outputStream.writeObject(nextMessage);
                } //catch (IOException | InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


}
