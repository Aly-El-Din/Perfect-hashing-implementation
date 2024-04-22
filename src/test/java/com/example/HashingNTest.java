package com.example;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import java.util.Random;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class HashingNTest 
{
    // repeat the insertion test
    @Test
    public void InsertStringTest()
    {
        HashingN<String> h = new HashingN<>(6000,true);
        int testSize = 5000;
        try {
            for (int i = 0; i < testSize; i++)
            //assertTrue(h.insert(new Generator().generateString()));\
            assertEquals("Inserted successfully", h.insert(new Generator().generateString()));
        } catch (Exception e) {
            assumeNoException(e);
        }
        assertEquals(testSize, h.count);
    }


    // insert multiple keys and search for them
    @Test
    public void SearchStringTest()
    {
        HashingN<String> h = new HashingN<>(6000,true);
        String[] keys = new String[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateString();
                h.insert(keys[i]);
            }
            System.out.println(h.count);

            for (String key : keys) {

                System.out.println("Searching for " + key);
                assertTrue(h.search(key));

            }
            for (int i = 0; i < 3000; i++) {
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
    public void DeleteStringTest() {
        HashingN<String> h = new HashingN<>(6000,true);
        String[] keys = new String[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateString();
                h.insert(keys[i]);
            }
            System.out.println(h.count);

            Random random = new Random();
            // delete 500 elements
            for (int i = 0; i < 500; i++) {
                int randomIndex = random.nextInt(3000);
                h.delete(keys[randomIndex]);
                assertTrue(h.validateDeletion(keys[randomIndex]));// Check the deleted element if it does not exist,
                                                                  // pass
            }

        } catch (Exception e) {
            assumeNoException(e);
        }

    }
    @Test
    public void InsertLongTest()
    {
        HashingN<Long> h = new HashingN<>(6000,false);
        int testSize = 5000;
        try {
            for (int i = 0; i < testSize; i++)
                assertEquals("Inserted successfully", h.insert(new Generator().generateLong()));
        } catch (Exception e) {
            assumeNoException(e);
        }
        assertEquals(testSize, h.count);
    }


    // insert multiple keys and search for them
    @Test
    public void SearchTest()
    {
        HashingN<Long> h = new HashingN<>(6000,false);
        Long[] keys = new Long[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateLong();
                h.insert(keys[i]);
            }
            System.out.println(h.count);

            for (Long key : keys) {

                System.out.println("Searching for " + key);
                assertTrue(h.search(key));

            }

        } catch (Exception e) {
            assumeNoException(e);
        }
    }


    @Test
    public void DeleteLongTest() {
        HashingN<Long> h = new HashingN<>(6000,false);
        Long[] keys = new Long[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateLong();
                h.insert(keys[i]);
            }
            System.out.println(h.count);


            // delete 500 elements
            for (int i = 0; i < 3000; i++) {
                h.delete(keys[i]);
                assertTrue(h.validateDeletion(keys[i]));// Check the deleted element if it does not exist,
                // pass
            }

        } catch (Exception e) {
            assumeNoException(e);
        }

    }
}
