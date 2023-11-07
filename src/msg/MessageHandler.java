package src.msg;
public class MessageHandler{
    public static Message stringToClass(String message){
        return new Message("a", "b", "c");
    }
}