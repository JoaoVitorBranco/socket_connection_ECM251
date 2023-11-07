package src.communication;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket{
    private final Socket socket;
    private final BufferedReader entrada;
    private final PrintWriter saida;

    public ClientSocket(final Socket socket) throws IOException{
        this.socket = socket;
        System.out.println("Conectado com " + socket.getRemoteSocketAddress() + "!");
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);
    }

    public String getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress().toString();
    }

    public void close(){
        try{
            entrada.close();
            saida.close();
            socket.close();
        }
        catch(IOException e){
            System.out.println("Erro ao fechar socket: " + e.getMessage());
        }
    }

    public String getMessage(){
        try {
            return entrada.readLine();
        } catch (IOException e) {
            System.out.println("Erro ao ler mensagem: " + e.getMessage());
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean sendMessage(String msg){
        saida.println(msg);
        return !saida.checkError();
    }

}