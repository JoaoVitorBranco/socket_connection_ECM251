package src.msg;
public class MessageHandler{
    public static Message stringToClass(String message) throws Exception{
        return new Message(StringHandler.stringToHashMap(message));
    }
}