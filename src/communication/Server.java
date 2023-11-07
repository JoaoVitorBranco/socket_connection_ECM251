package src.communication;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;

public class Server {
    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private final LinkedList<ClientSocket> clients = new LinkedList<>();

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT);
        clientConnectionLoop();
    }

    public void clientConnectionLoop() throws IOException{
        System.out.println("Aguardando conexões de clientes...");
        while (true){
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            clients.add(clientSocket);
            new Thread(() -> clientMessageLoop(clientSocket)).start();
        }

    }

    public void clientMessageLoop(ClientSocket clientSocket){
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                if("sair".equalsIgnoreCase(msg)) return;
                System.out.printf("<- Cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
                sendMessasgeToAll(clientSocket, msg);
            }
        }
        finally {
            clientSocket.close();
        }
    }

    private void sendMessasgeToAll(ClientSocket sender, String msg){
        Iterator<ClientSocket> iterator = clients.iterator();
        while(iterator.hasNext()){
            ClientSocket clientSocket = iterator.next();
            if(!sender.equals(clientSocket)){
                if(!clientSocket.sendMessage("Cliente " + sender.getRemoteSocketAddress() + " disse: " + msg)){
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("---- CONSOLE DO SERVIDOR ----");
        try{
            Server server = new Server();
            server.start();

        }
        catch(IOException e){
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
        System.out.println("---- FIM DO SERVIDOR ----");
    }


}
