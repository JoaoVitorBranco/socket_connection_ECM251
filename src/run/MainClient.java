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
        String content = "...";
        Message msg;
        do{
            op = JOptionPane.showInputDialog("Type a operation (broadcast, unicast, exit): ");
            client.canContinue();
            switch (op) {
                case "broadcast":
                    content = JOptionPane.showInputDialog("Type a message: ");
                    msg = new Message("broadcast", content, client.getAddress());
                    client.sendMessage(msg);
                    break;

                case "unicast":
                    client.unicastProtocolStart(); 
                    msg = new Message("unicast", content, client.getAddress());
                    client.sendMessage(msg);
                    break;

                case "exit":
                    break;

                default:
                    System.out.println("Invalid operation");
                    break;
            }

        } while(!op.equals("exit"));
        client.close();
    }
}
