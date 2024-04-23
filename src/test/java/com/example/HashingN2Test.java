package com.example;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class HashingN2Test {

    HashingN2<String> h = new HashingN2<>(true,Integer.MAX_VALUE ,6000);
    private int testSize = 6000;

    @Test
    public void InsertTestString()
    {
        
        try {
            for (int i = 0; i < testSize; i++){
            //assertTrue(h.insert(new Generator().generateString()));\
            String result = h.insert(new Generator().generateString());
            assertTrue(result.equals("Inserted successfully") || result.equals("Already exists in the table"));
            }
            
            
        System.out.println("Count: " + h.getCollisionCount());
        System.out.println("Duplicate Count: " + h.getDuplicateCount());
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void SearchTestString()
    {
        String[] keys = new String[testSize];
        try {
            for (int i = 0; i < testSize; i++) {
                keys[i] = new Generator().generateString();
                h.insert(keys[i]);
            }
            System.out.println(h.count);

            for (String key : keys) {

                System.out.println("Searching for " + key);
                assertTrue(h.search(key));

            }
            for (int i = 0; i < testSize; i++) {
                Generator gen = new Generator();
                String str = gen.generateString();
                if(gen.checkPresence(keys, str))
                    continue;
                System.out.println("Searching for " + keys[i]);
                assertFalse(h.search(str));
            }

        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void DeleteTestString()
    {

        String[] keys = new String[testSize];
        try {
            for (int i = 0; i < testSize; i++) {
                keys[i] = new Generator().generateString();
                h.insert(keys[i]);
            }
            System.out.println(h.count);

            for (String key : keys) {

                System.out.println("Deleting " + key);
                String result = h.delete(key);
                // check if the array keys has duplicate elements
                if(h.getDuplicateCount()!=0)
                assertTrue(result.equals("Element not found") || result.equals("Deleted successfully"));
                else
                assertTrue(result.equals("Deleted successfully"));

            }
            for (int i = 0; i < testSize; i++) {
                Generator gen = new Generator();
                String str = gen.generateString();
                if(gen.checkPresence(keys, str))
                    continue;
                System.out.println("Deleting " + keys[i]);
                assertEquals("Element not found", h.delete(str));
            }

        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void insertTestInt() {
        HashingN2<Integer> h = new HashingN2<>(false,Integer.MAX_VALUE ,6000);
        
        try {
            for (int i = 0; i < testSize; i++)
                //assertTrue(h.insert(new Generator().generateString()));\
                assertEquals("Inserted successfully", h.insert(new Random().nextInt()));
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void searchTestInt() {
        HashingN2<Integer> h = new HashingN2<>(false,Integer.MAX_VALUE ,6000);
        ArrayList<Integer> keys = new ArrayList<>();
        try {
            for (int i = 0; i < testSize; i++) {
                int key = new Random().nextInt();
                keys.add(key);
                h.insert(key);
            }
            System.out.println(h.count);

            for (int key : keys) {

                System.out.println("Searching for " + key);
                assertTrue(h.search(key));

            }
            for (int i = 0; i < testSize; i++) {
                int str = new Random().nextInt();
                if(keys.contains(str))
                    continue;
                System.out.println("Searching for " + keys.get(i));
                assertFalse(h.search(str));
            }

        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void deleteTestInt() {
        HashingN2<Integer> h = new HashingN2<>(false,Integer.MAX_VALUE ,6000);
        ArrayList<Integer> keys = new ArrayList<>();
        try {
            for (int i = 0; i < testSize; i++) {
                int key = new Random().nextInt();
                keys.add(key);
                h.insert(key);
            }
            System.out.println(h.count);

            for (int key : keys) {

                System.out.println("Deleting " + key);
                String result = h.delete(key);
                // check if the array keys has duplicate elements
                if(h.getDuplicateCount()!=0)
                assertTrue(result.equals("Element not found") || result.equals("Deleted successfully"));
                else
                assertTrue(result.equals("Deleted successfully"));

            }
            for (int i = 0; i < testSize; i++) {
                int str = new Random().nextInt();
                if(keys.contains(str))
                    continue;
                System.out.println("Deleting " + keys.get(i));
                assertEquals("Element not found", h.delete(str));
            }

        } catch (Exception e) {
            assumeNoException(e);
        }
    }
       

    
}
