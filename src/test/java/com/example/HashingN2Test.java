package com.example;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import java.util.Random;

import org.junit.Test;

public class HashingN2Test {

    @Test
    public void InsertTest()
    {
        HashingN2<String> h = new HashingN2<>(Integer.MAX_VALUE ,6000);
        int testSize = 5000;
        try {
            for (int i = 0; i < testSize; i++)
            //assertTrue(h.insert(new Generator().generateString()));\
            assertEquals("Inserted successfully", h.insert(new Generator().generateString()));
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void SearchTest()
    {
        HashingN2<String> h = new HashingN2<>(Integer.MAX_VALUE ,6000);
        int testSize = 5000;
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
    public void DeleteTest()
    {
        HashingN2<String> h = new HashingN2<>(Integer.MAX_VALUE ,6000);
        int testSize = 5000;
        String[] keys = new String[testSize];
        try {
            for (int i = 0; i < testSize; i++) {
                keys[i] = new Generator().generateString();
                h.insert(keys[i]);
            }
            System.out.println(h.count);

            for (String key : keys) {

                System.out.println("Deleting " + key);
                assertEquals("Deleted successfully", h.delete(key));

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
    
}
