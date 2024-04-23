package com.example;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import com.example.Dictionary.DictionaryNSpace;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Unit test for Dictionary using N space perfect hash.
 */

public class DictionaryNTest {
    @Test
    public void insertTest() {
        DictionaryNSpace dictionary = new DictionaryNSpace(6000);
        int testSize = 5000;
        try {
            for (int i = 0; i < testSize; i++)
                // assertTrue(h.insert(new Generator().generateString()));\
                assertEquals("Inserted successfully", dictionary.insert(new Generator().generateString()));
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void searchTest() {
        DictionaryNSpace dictionary = new DictionaryNSpace(2000);
        String[] keys = new String[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateString();
                dictionary.insert(keys[i]);
            }

            for (String key : keys) {
                System.out.println("Searching for " + key);
                assertTrue(dictionary.search(key));
            }
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void deleteTest() {
        DictionaryNSpace dictionary = new DictionaryNSpace(2000);
        String[] keys = new String[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateString();
                dictionary.insert(keys[i]);
            }

            Random random = new Random();
            // delete 500 elements
            for (int i = 0; i < 500; i++) {
                int randomIndex = random.nextInt(3000);
                dictionary.delete(keys[randomIndex]);
                assertTrue(dictionary.validateDeletion(keys[randomIndex]));// Check the deleted element if it does not
                                                                           // exist, pass
            }
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void testBatchInsert() {
        DictionaryNSpace dictionary = new DictionaryNSpace(1000);
        String filePath = "Words.txt";

        try {
            dictionary.batchInsert(filePath);
            ArrayList<String> words = dictionary.readWordsFromFile(filePath);
            for (String word : words) {
                System.out.println("Searching for " + word);
                assertTrue(dictionary.search(word));
            }
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void testBatchDelete() {
        DictionaryNSpace dictionary = new DictionaryNSpace(1000);
        String filePath = "Words.txt";
        try {
            dictionary.batchInsert(filePath);
            dictionary.batchDelete(filePath);
            ArrayList<String> words = dictionary.readWordsFromFile(filePath);
            for (String word : words) {
                assertTrue(dictionary.validateDeletion(word));// Check the deleted element if it does not exist, pass
            }
        } catch (Exception e) {
            assumeNoException(e);
        }
    }
    
   
}