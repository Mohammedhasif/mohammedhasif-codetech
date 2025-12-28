import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatServer {
    private static final int PORT = 1234;
    private static final Set<PrintWriter> clientWriters = new CopyOnWriteArraySet<>();

    public static void main(String[] args) {
        int port = PORT;
        if (args.length > 0) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        System.out.println("Chat Server started on port " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(port));
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (Scanner in = new Scanner(socket.getInputStream());
                 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {

                out = pw;
                clientWriters.add(out);

                while (in.hasNextLine()) {
                    String message = in.nextLine();
                    System.out.println("Received: " + message);
                    // broadcast
                    for (PrintWriter writer : clientWriters) {
                        writer.println(message);
                    }
                }
            } catch (Exception e) {
                System.out.println("User disconnected: " + e.getMessage());
            } finally {
                if (out != null) {
                    clientWriters.remove(out);
                    out.close();
                }
                try {
                    socket.close();
                } catch (IOException ignored) {}
            }
        }
    }
}