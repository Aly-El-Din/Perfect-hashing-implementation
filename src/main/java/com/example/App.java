package com.example;

import java.util.HashMap;
import java.util.Random;






public class App 
{
    public static void main( String[] args )
    {
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] k = new int[1000];
// outputs collisions to a file
        for (int i = 0; i < 1000;i++){
        ////
            Random rand = new Random();
            int n = rand.nextInt(1000000);
            k[i] = n;
            if (map.containsKey(n)) {
                System.out.println("Collision at index: " + i);
                continue;
            }
            map.put(n, i);
           }            
        
    }
}
