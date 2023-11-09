package src.communication;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import src.msg.Message;
import src.msg.MessageHandler;
import src.msg.StringHandler;

public class Client implements Runnable{
    private ClientSocket clientSocket;
    private boolean unicastProtocolRunning = false;

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

                System.out.println("Message received (not unicast print): " + message.toString());

                if(message.getSender().equals("localhost")){ // First communications that tells to the client what is his address
                    clientSocket.address = message.getContent();
                    System.out.println("My address is " + clientSocket.address);
                }
                else if(message.getSender().equals("unicast")){
                    // Have to ask the user the address of the client he wants to send a message
                    System.out.println("Entrando no StringHandler.stringToArr");
                    ArrayList<String> addresses = StringHandler.stringToArr(message.getContent());
                    
                    if(addresses.size() == 0){ // Checking if this is the only client in the network
                        JOptionPane.showMessageDialog(null, "There is no other client in the network to send a message.");
                        
                    }
                    else{
                        // Creating a string that contains all the addresses as a bullet list
                        String addressesString = "";
                        for(int i = 0; i < addresses.size(); i++){
                            addressesString += (i) + ". " + addresses.get(i) + "\n";
                        }
                        
                        // Asking the user about the address
                        Integer idx = -1;
                        do{
                            try{
                                idx = Integer.parseInt(JOptionPane.showInputDialog("Type the index (0,1,2, ...) of the client's address that you want to send a message:\n" + addressesString));
                            }
                            catch (Exception err) {}
    
                        } while(idx < 0 || idx >= addresses.size());
                        String address = addresses.get(idx);
    
                        // Asking the user about the content
                        String content = JOptionPane.showInputDialog("Type the content of the message you want to send to " + address + ":");
    
                        // Sending the message
                        Message msgToSend = new Message(address, content, clientSocket.address);
                        sendMessage(msgToSend);
                    }

                    // Stoping unicast protocol
                    this.unicastProtocolRunning = false;

                }   
                else{
                    // System.out.println("Message received from " + message.getSender() + ": " + message.getContent());
                }
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

    public void canContinue(){
        while(this.unicastProtocolRunning){
        }
    }

    public void unicastProtocolStart(){
        this.unicastProtocolRunning = true;
    }

}
