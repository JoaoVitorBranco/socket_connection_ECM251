package src.run;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import src.communication.Client;
import src.window.ClientScreen;

public class MainClient {
    public static void main(String[] args) {
        JTextArea chatTextField = new JTextArea(20, 50);
        chatTextField.setEditable(false);
        Client client = new Client();

        try{
            client.start(chatTextField);
        }
        catch (IOException err){
            JOptionPane.showMessageDialog(null, "Error in communication: " + err.getMessage());
        }

        try{
            Thread.sleep(500);
        }
        catch(Exception e){
            System.out.println("Error in thread sleep: " + e.getMessage());
        }
        ClientScreen clientScreen = new ClientScreen(client, chatTextField);
    }
}
