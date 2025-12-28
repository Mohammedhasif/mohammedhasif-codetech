import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to the chat server!");

            new Thread(() -> {
                try (Scanner in = new Scanner(socket.getInputStream())) {
                    while (in.hasNextLine()) {
                        System.out.println("Server: " + in.nextLine());
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                }
            }).start();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner consoleInput = new Scanner(System.in);
            
            System.out.println("Type your messages (Press Enter to send):");
            while (consoleInput.hasNextLine()) {
                out.println(consoleInput.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Could not connect to server. Is it running?");
        }
    }
}
