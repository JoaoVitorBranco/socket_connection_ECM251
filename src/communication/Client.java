package src.communication;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

public class Client implements Runnable{
    private ClientSocket clientSocket;

    public Client(){
    }

    public void start() throws IOException{
        try{
            clientSocket = new ClientSocket(new Socket(Server.ADDRESS, Server.PORT));
            new Thread(this).start();
        }
        catch(Exception e){
            System.out.println("Erro ao iniciar cliente: " + e.getMessage());
            System.exit(1);
            clientSocket.close();
        }
    }

    @Override
    public void run(){
        String msg;
        while((msg = clientSocket.getMessage()) != null){
        }
    }

    public void sendMsg(String msg){
        clientSocket.sendMessage(msg);
    }

    public void close(){
        clientSocket.close();
    }

}
