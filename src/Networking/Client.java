package Networking;

import java.io.*;
import java.net.Socket;

public class Client {
    private String name;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket, String username) throws IOException {
        this.socket = socket;
        this.name = username;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void  listenForMessages() throws IOException {
        String inputFromClient;
        while(socket.isConnected()){
            try {
                inputFromClient = bufferedWriter.write();
            } catch (IOException e) {
                break;
            }
        }
    }
}
