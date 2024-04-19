package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
   

    // repeat the insertion test
    @Test
    public void RandomInsertTest()
    {
        HashingN<String> h = new HashingN<>(1000);

        try {
            for (int i = 0; i < 5000; i++) {
                h.insert(new Generator().generateString());
            }
            h.print();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    // insert multipe keys and search for them
    @Test
    public void SearchTest()
    {
       
        HashingN<String> h = new HashingN<>(100);
        String[] keys = new String[100];
        try {
            for (int i = 0; i < 100; i++) {
                keys[i] = new Generator().generateString();
                h.insert(keys[i]);
            }
            h.print();
            
            for (int i = 0; i < 1000; i++) {
                System.out.println("Searching for " + keys[i]);
                assertTrue(h.search(keys[i]));
            }
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

}
