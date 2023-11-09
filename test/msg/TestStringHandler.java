package test.msg;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
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
    @DisplayName("Testing {banana: 1, apple: 2, orange: 3, mango: 4, pineapple: 4 3 2 5}")
    public void test9(){
        String str = "{banana: 1, apple: 2, orange: 3, mango: 4, pineapple: 4 3 2 5}";
        try{
            HashMap<String, String> hashMap = StringHandler.stringToHashMap(str);
            assertEquals(hashMap.size(), 5);
            assertEquals(hashMap.get("banana"), "1");
            assertEquals(hashMap.get("apple"), "2");
            assertEquals(hashMap.get("orange"), "3");
            assertEquals(hashMap.get("mango"), "4");
            assertEquals(hashMap.get("pineapple"), "4 3 2 5");
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
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}. The string is: {banana: 1, apple: 2, orange: 3,");
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
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}. The string is: adoleta");
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
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}. The string is: {adoleta le peti peti pola}");
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
            assertEquals(e.getMessage(), "String must be in the format of {key:value, key:value}. The string is: {adoleta}");
        }
    }

    @Test
    @DisplayName("Testing []")
    public void test10(){
        String str = "[]";
        try{
            ArrayList<String> arr = StringHandler.stringToArr(str);
            assertEquals(arr.size(), 0);
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Testing [1]")
    public void test11(){
        String str = "[1]";
        try{
            ArrayList<String> arr = StringHandler.stringToArr(str);
            assertEquals(arr.size(), 1);
            assertEquals(arr.get(0), "1");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing [1-2]")
    public void test12(){
        String str = "[1-2]";
        try{
            ArrayList<String> arr = StringHandler.stringToArr(str);
            assertEquals(arr.size(), 2);
            assertEquals(arr.get(0), "1");
            assertEquals(arr.get(1), "2");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing [192.168.0.25/4685-192.168.0.42/192]")
    public void test13(){
        String str = "[192.168.0.25/4685-192.168.0.42/192]";
        try{
            ArrayList<String> arr = StringHandler.stringToArr(str);
            assertEquals(arr.size(), 2);
            assertEquals(arr.get(0), "192.168.0.25/4685");
            assertEquals(arr.get(1), "192.168.0.42/192");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing ArrayList({})")
    public void test14(){
        ArrayList<String> arr = new ArrayList<String>();
        try{
            String str = StringHandler.arrToString(arr);
            assertEquals(str, "[]");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing ArrayList({saveiro, gol, fusca, uno, celta})")
    public void test15(){
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList("saveiro", "gol", "fusca", "uno", "celta"));
        try{
            String str = StringHandler.arrToString(arr);
            assertEquals(str, "[saveiro-gol-fusca-uno-celta]");
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing ArrayList to String to ArrayList")
    public void test16(){
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList("127.0.0.1/55163", "127.0.0.1/55164"));
        try{
            String str = StringHandler.arrToString(arr);
            ArrayList<String> arr2 = StringHandler.stringToArr(str);
            assertEquals(arr2.size(), 2);
            assertEquals(arr2.get(0), arr.get(0));
            assertEquals(arr2.get(1), arr.get(1));
        }
        catch (Exception e){
            assertEquals(false, e.getMessage());
        }
    }
}
