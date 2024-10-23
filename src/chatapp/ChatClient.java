package chatapp;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            // Connect to the server running on localhost at port 5000
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to the server.");

            // Input and output streams to receive/send messages
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Reading input from console to send to server
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;

            // Chat loop
            while (true) {
                // Get client message from console and send it to server
                System.out.print("Client: ");
                clientMessage = consoleInput.readLine();
                output.println(clientMessage);

                // Exit if client sends "bye"
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Closing connection...");
                    break;
                }

                // Read server message
                serverMessage = input.readLine();
                System.out.println("Server: " + serverMessage);

                // Exit if server sends "bye"
                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Server disconnected.");
                    break;
                }
            }

            // Close sockets and streams
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
