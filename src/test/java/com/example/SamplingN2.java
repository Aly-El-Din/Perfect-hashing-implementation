package com.example;
import com.example.HashTableADT.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class SamplingN2 {

    // samlping data about space and time complexity of HashingN2
    @Test
    public void complexityAnalysisTest() {
        int space = 0; // space complexity
        long totalInsertionTime = 0; // total time taken to insert entries
        long totalSearchTime = 0; // total time taken to search entries
        int sampleSize = 5; // number of samples to be taken of a given input size
        int inputSize = 20000; // random input size (size of entries to be inserted)
        int c = new Random().nextInt(9) + 1; // random constant c assumed to be between 1 - 9
        int maxInputSize = 20001; // maximum input size
        int[] spaceComplexities = new int[sampleSize];
        long[] averageTimesPerInsertion = new long[sampleSize];
        long[] averageTimesPerSearch = new long[sampleSize];
        ArrayList<String> values = new ArrayList<>();
        int[] totalCollisions = new int[sampleSize];

        for (int x = inputSize; x < maxInputSize; x += 10000) {

            for (int i = 0; i < sampleSize; i++) {
                HashingN2<String> hash = new HashingN2<>(true,Long.MAX_VALUE, inputSize);
                long startInsert = System.nanoTime();

                Generator generator = new Generator();
                for (int j = 0; j < x; j++){
                    String word = generator.generateString();
                    hash.insert(word);
                    values.add(word);
                }
                long endInsert = System.nanoTime();

                totalInsertionTime = (endInsert - startInsert);

                long startSearch = System.nanoTime();

                for (int j = 0; j < x; j++){
                    hash.search(values.get(j));
                }
                long endSearch = System.nanoTime();

                totalSearchTime = (endSearch - startSearch);

            
                // Calculate average time per insertion
                averageTimesPerInsertion[i] = totalInsertionTime;
                averageTimesPerSearch[i] = totalSearchTime;

                // Calculate space complexity
                
                spaceComplexities[i] = hash.getHashTableSize();

                // Calculate total collisions
                totalCollisions[i] = hash.getCollisionCount() ;
            }

            // Write data to CSV file
            try {
                new CSVWriter().writeData("O(n2) sample/data".concat(String.valueOf(x).concat(".csv")), inputSize, sampleSize,
                        averageTimesPerInsertion,averageTimesPerSearch,totalCollisions, spaceComplexities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
