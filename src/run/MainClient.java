package src.run;

import java.io.IOException;

import javax.swing.JOptionPane;

import src.communication.Client;
import src.msg.Message;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();
        try{
            client.start();
        }
        catch (IOException err){
            JOptionPane.showMessageDialog(null, "Error in communication: " + err.getMessage());
        }

        String op = "";
        op = JOptionPane.showInputDialog("Type a operation (broadcast, unicast, exit): ");
        do{
            switch (op) {
                case "broadcast":
                    String content = JOptionPane.showInputDialog("Type a message: ");
                    Message msg = new Message(client.getAddress(), content, "broadcast");
                    client.sendMessage(msg);
                    break;

                case "unicast":
                    
                    break;

                case "exit":
                    
                    break;

                default:
                    System.out.println("Invalid operation");
                    break;
            }
            op = JOptionPane.showInputDialog("Type a operation (broadcast, unicast, exit): ");

        } while(!op.equals("exit"));
        client.close();
    }
}
