package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    public Server (ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() throws IOException {
        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("A new client connected");
            ClientHandler clientHandler = new ClientHandeler(clientSocket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.start();
    }

    public void closeServer() throws IOException {
        serverSocket.close();
    }

    public void broadCast(String message) {

    }
}
