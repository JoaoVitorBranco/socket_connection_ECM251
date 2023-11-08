package src.communication;
import java.io.IOException;
import java.net.Socket;

import src.msg.Message;
import src.msg.MessageHandler;

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
            try{
                Message message = MessageHandler.stringToClass(msg);
                if(message.getSender().equals("localhost")){
                    clientSocket.address = message.getContent();
                }
                System.out.println(message.toString());
            }
            catch(Exception e){
                System.out.println("Error in the message received: " + e.getMessage());
                clientSocket.close();
            }
        }
    }

    public void sendMessage(Message msg){
        String msgString = msg.toString();
        clientSocket.sendMessage(msgString);
    }

    public void close(){
        clientSocket.close();
    }

    public String getAddress(){
        return clientSocket.address;
    }

}
