package com.example;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import org.junit.Test;

public class DictionaryN2Test {

    @Test
    public void testInsert() {
        DictionaryN2Space d = new DictionaryN2Space(6000);
        int testSize = 5000;
        try {
            for (int i = 0; i < testSize; i++)
                assertEquals("Inserted successfully", d.insert(new Generator().generateString()));
        } catch (Exception e) {
            assumeNoException(e);
        }
        assertEquals(testSize, d.getCount());
    }

    @Test
    public void testSearch() {
        DictionaryN2Space d = new DictionaryN2Space(6000);
        String[] keys = new String[3000];
        try {
            for (int i = 0; i < 3000; i++) {
                keys[i] = new Generator().generateString();
                d.insert(keys[i]);
            }
            System.out.println(d.getCount());

            for (String key : keys) {

                System.out.println("Searching for " + key);
                assertTrue(d.search(key));

            }
            for (int i = 0; i < 3000; i++) {
                Generator gen = new Generator();
                String str = gen.generateString();
                if (gen.checkPresence(keys, str))
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
        DictionaryN2Space d = new DictionaryN2Space(6000);
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
        DictionaryN2Space d = new DictionaryN2Space(6000);
        try {
            d.batchInsert("Words.txt");
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    // read from file
    @Test
    public void batchDeleteTest() {
        DictionaryN2Space d = new DictionaryN2Space(6000);
        try {
            d.batchInsert("Words.txt");
            d.batchDelete("Words.txt");
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

}
