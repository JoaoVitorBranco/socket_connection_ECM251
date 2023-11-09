package src.msg;

import java.util.ArrayList;

public class MessageHandler{
    public static Message stringToClass(String message) throws Exception{
        return new Message(StringHandler.stringToHashMap(message));
    }

    public static ArrayList<String> stringToArr(String message) throws Exception{
        return StringHandler.stringToArr(message);
    }
}