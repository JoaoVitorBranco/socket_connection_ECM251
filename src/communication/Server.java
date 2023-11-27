package src.communication;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import src.msg.Message;
import src.msg.MessageHandler;
import src.msg.StringHandler;

public class Server {
    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private final HashMap<String, ClientSocket> clients = new HashMap<String, ClientSocket>();

    public void start() throws Exception {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT);
        clientConnectionLoop();
    }

    public void clientConnectionLoop() throws Exception{
        System.out.println("Aguardando conexões de clientes...");
        while (true){
            // Changing the address value in ClientSocket ServerSide and ClientSide
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            String newAddress = clientSocket.address.replace('/',' ').replace(':','/').trim();
            clientSocket.setAddress(newAddress);
            clients.put(newAddress, clientSocket);
            sendMessage(clientSocket, new Message(newAddress, newAddress, "localhost"));
            
            try{
                new Thread(() -> clientMessageLoop(clientSocket)).start();
        
            } catch (Exception e) {
                
            }
        }
    }

    public void clientMessageLoop(ClientSocket clientSocket){
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                // Initialize message
                Message message = null;
                try {
                    message = MessageHandler.stringToClass(msg);
                } catch (Exception e) {
                    return ;
                }

                // Handle message content (exit, broadcast, unicast)
                switch (message.getTarget()) {
                    case "exit": // exit
                        return ;

                    case "broadcast": // broadcast
                        this.sendMessageToAll(clientSocket, message);
                        break;

                    case "unicast": // unicast request of addresses
                        this.sendAddressesToSender(clientSocket);
                        break;

                    default: // unicast
                        ClientSocket target = this.clients.get(message.getTarget());
                        if(target != null){
                            sendMessage(target, message);
                        }
                        break;
                }
                if("sair".equalsIgnoreCase(msg)) return;
                System.out.printf("<- Cliente %s: %s\n", clientSocket.address, msg);
            }
        }
        finally {
            clientSocket.close();
            clients.remove(clientSocket.address);
        }
    }

    private void sendMessageToAll(ClientSocket sender, Message msg){
        for (String address : this.clients.keySet()) {
            if(!address.equals(msg.getSender())){
                sendMessage(this.clients.get(address), msg);
            }
        }
    }

    private void sendAddressesToSender(ClientSocket sender){
        ArrayList<String> addresses = new ArrayList<String>();
        for (String string : clients.keySet()) {
            if(!sender.address.equals(string)){
                addresses.add(string);
            }
        }

        String content = "";
        try{
            content = StringHandler.arrToString(addresses);
        } catch (Exception e) {
        }

        Message msg = new Message(sender.address, content, "unicast");
        System.out.println("Enviando os seguintes endereços para o cliente: " + content);
        sendMessage(sender, msg);

    }

    private void sendMessage(ClientSocket clientSocket, Message msg){
        String msgString = msg.toString();
        clientSocket.sendMessage(msgString);
    }

    public static void main(String[] args) {
        System.out.println("---- CONSOLE DO SERVIDOR ----");
        try{
            Server server = new Server();
            try {
                server.start();
            } catch (Exception e) {
            }

        }
        catch(Exception e){
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
        System.out.println("---- FIM DO SERVIDOR ----");
    }


}
