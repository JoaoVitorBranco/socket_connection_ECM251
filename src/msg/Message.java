package src.msg;
import java.util.HashMap;

public class Message {
    private String target;
    private String content;
    private String sender;

    public Message(String target, String content, String sender) {
        this.target = target;
        this.content = content;
        this.sender = sender;
    }

    public Message(HashMap<String, String> hashMap) throws Exception{
        this.target = hashMap.get("target");
        if (this.target == null){
            throw new Exception("Message must have a target");
        }

        this.content = hashMap.get("content");
        if (this.content == null){
            throw new Exception("Message must have content");
        }

        this.sender = hashMap.get("sender");
        if (this.sender == null){
            throw new Exception("Message must have a sender");
        }
    }

    public String getTarget() {
        return this.target;
    }

    public String getContent() {
        return this.content;
    }

    public String getSender() {
        return this.sender;
    }

    public String toString(){
        return "{ target: " +  this.target + ", content: " + this.content + ", sender: " + this.sender + " }";
    }
}
