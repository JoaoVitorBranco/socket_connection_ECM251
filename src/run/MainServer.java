package src.run;

import java.io.IOException;

import src.communication.Server;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("---- SERVER ----");
        try{
            Server server = new Server();
            server.start();

        }
        catch(IOException e){
            System.out.println("Error in server initialization: " + e.getMessage());
        }
        System.out.println("---- SERVER CLOSED ----");
    }
}
