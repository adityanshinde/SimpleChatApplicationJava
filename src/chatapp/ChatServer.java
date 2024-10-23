package chatapp;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Create server socket that listens on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is waiting for client...");

            // Accept the connection from the client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Input and output streams to receive/send messages
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Reading input from console to send to client
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;

            // Chat loop
            while (true) {
                // Read client message
                clientMessage = input.readLine();
                System.out.println("Client: " + clientMessage);

                // Exit loop if client sends "bye"
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                // Get server response from console and send it to client
                System.out.print("Server: ");
                serverMessage = consoleInput.readLine();
                output.println(serverMessage);

                // Exit if server sends "bye"
                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Closing connection...");
                    break;
                }
            }

            // changes done in test_branch

            // Close sockets and streams
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
