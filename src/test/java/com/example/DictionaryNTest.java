package com.example;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import org.junit.Test;

public class DictionaryNTest {

    @Test
    public void testInsert() {
       DictionaryNSpace d = new DictionaryNSpace(6000);
        int testSize = 5000;
        try {
            for (int i = 0; i < testSize; i++)
                assertEquals("Inserted successfully", d.insert(new Generator().generateString()));
        } catch (Exception e) {
            assumeNoException(e);
        }
        assertEquals(testSize, d.count());
    }

    @Test
    public void testSearch() {
        DictionaryNSpace d = new DictionaryNSpace(6000);
        String[] keys = new String[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateString();
                d.insert(keys[i]);
            }
            System.out.println(d.count());

            for (String key : keys) {

                System.out.println("Searching for " + key);
                assertTrue(d.search(key));

            }
            for (int i = 0; i < 3000; i++) {
                Generator gen = new Generator();
                String str = gen.generateString();
                if(gen.checkPresence(keys, str))
                    continue;
                System.out.println("Searching for " + keys[i]);
                assertFalse(d.search(str));
            }
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void testDelete() {
        DictionaryNSpace d = new DictionaryNSpace(6000);
        int testSize = 5000;

        String[] str = new String[testSize];

        try {
            for (int i = 0; i < testSize; i++) {
                str[i] = new Generator().generateString();
                d.insert(str[i]);
            }
            for (int i = 0; i < testSize; i++) {
                System.out.println("Deleting " + str[i]);
                assertEquals("Deleted successfully", d.delete(str[i]));
            }
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    // read from file
    @Test
    public void batchInsertTest() {
       DictionaryNSpace d = new DictionaryNSpace(6000);
        try {
            d.batchInsert("Words.txt");
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    // read from file
    @Test
    public void batchDeleteTest() {
        DictionaryNSpace d = new DictionaryNSpace(6000);
        try {
            d.batchInsert("Words.txt");
            d.batchDelete("Words.txt");
        } catch (Exception e) {
            assumeNoException(e);
        }
    }


}