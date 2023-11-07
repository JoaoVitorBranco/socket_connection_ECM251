package test.msg;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import src.msg.StringHandler;



public class TestStringHandler {
    @Test
    @DisplayName("Testing {}")
    public void test1() {
        String str = "{}";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(hashMap.size(), 0);
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing {banana: 1}")
    public void test2(){
        String str = "{banana: 1}";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(hashMap.size(), 1);
            assertEquals(hashMap.get("banana"), "1");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing {banana: 1, apple: 2}")
    public void test3(){
        String str = "{banana: 1, apple: 2}";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(hashMap.size(), 2);
            assertEquals(hashMap.get("banana"), "1");
            assertEquals(hashMap.get("apple"), "2");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing {banana: 1, apple: 2, orange: 3, mango: 4, pineapple: 5}")
    public void test4(){
        String str = "{banana: 1, apple: 2, orange: 3, mango: 4, pineapple: 5}";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(hashMap.size(), 5);
            assertEquals(hashMap.get("banana"), "1");
            assertEquals(hashMap.get("apple"), "2");
            assertEquals(hashMap.get("orange"), "3");
            assertEquals(hashMap.get("mango"), "4");
            assertEquals(hashMap.get("pineapple"), "5");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing error {banana: 1, apple: 2, orange: 3,}")
    public void test5(){
        String str = "{banana: 1, apple: 2, orange: 3,";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(false, true);
        }
        catch (Exception e){
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}");
        }
    }

    @Test
    @DisplayName("Testing error adoleta")
    public void test6(){
        String str = "adoleta";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(false, true);
        }
        catch (Exception e){
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}");
        }
    }

    @Test
    @DisplayName("Testing error {adoleta le peti peti pola}")
    public void test7(){
        String str = "{adoleta le peti peti pola}";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(false, true);
        }
        catch (Exception e){
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}");
        }
    }

    @Test
    @DisplayName("Testing error {adoleta}")
    public void test8(){
        String str = "{adoleta}";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(false, true);
        }
        catch (Exception e){
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}");
        }
    }
}
