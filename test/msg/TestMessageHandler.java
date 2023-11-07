package test.msg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import src.msg.Message;
import src.msg.MessageHandler;

public class TestMessageHandler {
    @Test
    @DisplayName("Testing { target: client1, content: mensagem 123 456, sender: client2 }")
    public void test1(){
        String str = "{ target: client1, content: mensagem 123 456, sender: client2 }";
        try{
            Message message = MessageHandler.stringToClass(str);
            assertEquals(message.getTarget(), "client1");
            assertEquals(message.getContent(), "mensagem 123 456");
            assertEquals(message.getSender(), "client2");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Testing { target: client1, content: mensagem eh \"banana e macaco\", sender: client2 }")
    public void test2(){
        String str = "{ target: client1, content: mensagem eh \"banana e macaco\", sender: client2 }";
        try{
            Message message = MessageHandler.stringToClass(str);
            assertEquals(message.getTarget(), "client1");
            assertEquals(message.getContent(), "mensagem eh \"banana e macaco\"");
            assertEquals(message.getSender(), "client2");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing error { targe: client1, content: mensagem eh \"banana e macaco\", sender: client2 }")
    public void test3(){
        String str = "{ targe: client1, content: mensagem eh \"banana e macaco\", sender: client2 }";
        try{
            Message message = MessageHandler.stringToClass(str);
            assertEquals(false, true);
        }
        catch (Exception e){
            assertEquals("Message must have a target", e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing error { target: client1, cont: mensagem eh \"banana e macaco\", sender: client2 }")
    public void test4(){
        String str = "{ target: client1, cont: mensagem eh \"banana e macaco\", sender: client2 }";
        try{
            Message message = MessageHandler.stringToClass(str);
            assertEquals(false, true);
        }
        catch (Exception e){
            assertEquals("Message must have content", e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing error { target: client1, content: mensagem eh \"banana e macaco\", der: client2 }")
    public void test5(){
        String str = "{ target: client1, content: mensagem eh \"banana e macaco\", der: client2 }";
        try{
            Message message = MessageHandler.stringToClass(str);
            assertEquals(false, true);
        }
        catch (Exception e){
            assertEquals("Message must have a sender", e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Testing message to string to message")
    public void test6(){
        String target = "client1";
        String content = "mensagem nova";
        String sender = "client2";

        Message startMessage = new Message(target, content, sender);

        String str = startMessage.toString();

        try{
            Message finalMessage = MessageHandler.stringToClass(str);
            assertEquals(finalMessage.getTarget(), target);
            assertEquals(finalMessage.getContent(), content);
            assertEquals(finalMessage.getSender(), sender);
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

}
