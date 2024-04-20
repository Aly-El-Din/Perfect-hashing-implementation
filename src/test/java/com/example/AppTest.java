package com.example;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    // repeat the insertion test
    @Test
    public void RandomInsertTest()
    {
        HashingN<String> h = new HashingN<>(6000);
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


    // insert multipe keys and search for them
    @Test
    public void SearchTest()
    {
        HashingN<String> h = new HashingN<>(6000);
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

        } catch (Exception e) {
            assumeNoException(e);
        }
    }
    @Test
    public void DeleteTest(){
        HashingN<String> h = new HashingN<>(6000);
        String[] keys = new String[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateString();
                h.insert(keys[i]);
            }
            System.out.println(h.count);

            Random random=new Random();
            //delete 500 elements
            for(int i=0;i<500;i++){
                int randomIndex=random.nextInt(3000);
                h.delete(keys[randomIndex]);
                assertTrue(h.validateDeletion(keys[randomIndex]));//Check the deleted element if it does not exist, pass
            }

        } catch (Exception e) {
            assumeNoException(e);
        }

    }

}