package com.example;

import java.util.HashMap;
import java.util.Random;
public class App 
{
    public static void main( String[] args )
    {
        HashingN<String> h = new HashingN<>(10);
        String[] strings = new String[10];
        try {
            for (int i = 0; i < 5; i++) {
                strings[i] = new Generator().generateString();
                h.insert(strings[i]);

            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        System.out.println(h.count==h.review());

        h.print();



//        boolean failed=false;
//        for (String s : strings) {
//            if(!h.search(s)) {
//                failed=true;
//                break;
//            }
//        }
//        if (!failed)
//             System.out.println("Search test passed");
//        else System.out.println("Search failed");

    }
}
