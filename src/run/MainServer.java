package src.run;

import src.communication.Server;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("---- SERVER ----");
        try{
            Server server = new Server();
            server.start();

        }
        catch(Exception e){
            System.out.println("Error in server initialization: " + e.getMessage());
        }
        System.out.println("---- SERVER CLOSED ----");
    }
}
