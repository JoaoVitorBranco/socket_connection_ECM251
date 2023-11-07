package src.msg;
import java.util.HashMap;

public class StringHandler {
    public static HashMap<String, String> stringToHashMap(String str) throws Exception{
        try{
            if(str.trim() == "{}"){
                return new HashMap<String, String>();
            }
            else if(str.charAt(0) != '{' || str.charAt(str.length()-1) != '}'){
                throw new Exception("String must be in the format of {key:value, key:value}");
            }
            else{
                HashMap<String, String> hashMap = new HashMap<String, String>();
                String[] pairs = str.substring(1, str.length()-1).split(",");
    
                for (String pair : pairs){
                    String[] keyValue = pair.split(":");
                    hashMap.put(keyValue[0].trim(), keyValue[1].trim());
                }
                return hashMap;
            }
        }
        catch(Exception e){
            throw new Exception("String must be in the format of {key:value, key:value}");
        }
    }
}
