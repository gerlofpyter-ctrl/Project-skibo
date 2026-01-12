package Networking;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientName;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        clientHandlers.add(this);
    }
    @Override
public void run(){
        String inputFromClient;
        while(clientSocket.isConnected()){
            try {
                inputFromClient = bufferedWriter.write();
            } catch (IOException e) {
                break;
            }
        }
}
public void broadCastMessage(String message){
        for (ClientHandler clientHandler : clientHandlers){
            try{
                clientHandler.bufferedWriter.write(message);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            } catch (IOException e) {
                break;
            }
        }
}
}
