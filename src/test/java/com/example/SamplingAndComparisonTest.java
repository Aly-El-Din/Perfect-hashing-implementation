package com.example;
import com.example.HashTableADT.*;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class SamplingAndComparisonTest {

    // samlping data about space and time complexity of HashingN
    @Test
    public void complexityAnalysisTest() {
        int space = 0; // space complexity
        long totalInsertionTime = 0; // total time taken to insert entries
        long totalSearchTime = 0; // total time taken to search entries
        int sampleSize = 5; // number of samples to be taken of a given input size
        int inputSize = 1000; // random input size (size of entries to be inserted)
        int c = new Random().nextInt(9) + 1; // random constant c assumed to be between 1 - 9
        int maxInputSize = 10001; // maximum input size
        int[] spaceComplexities = new int[sampleSize];
        long[] averageTimesPerInsertion = new long[sampleSize];
        long[] averageTimesPerSearch = new long[sampleSize];
        ArrayList<String> values = new ArrayList<>();
        int[] totalCollisions = new int[sampleSize];

        for (int x = inputSize; x < maxInputSize; x += 1000) {

            for (int i = 0; i < sampleSize; i++) {
                HashingN<String> hash = new HashingN<>(2 * inputSize);
                long startInsert = System.currentTimeMillis();

                Generator generator = new Generator();
                for (int j = 0; j < x; j++){
                    String word = generator.generateString();
                    hash.insert(word);
                    values.add(word);
                }
                long endInsert = System.currentTimeMillis();

                totalInsertionTime = (endInsert - startInsert);

                long startSearch = System.currentTimeMillis();

                for (int j = 0; j < x; j++){
                    hash.search(values.get(j));
                }
                long endSearch = System.currentTimeMillis();

                totalSearchTime = (endSearch - startSearch);

                int[] N = hash.getN();
                for (int k = 0; k < 2 * inputSize; k++) {
                    space += N[k];
                } 

                // Calculate average time per insertion
                averageTimesPerInsertion[i] = totalInsertionTime;
                averageTimesPerSearch[i] = totalSearchTime;

                // Calculate space complexity
                
                spaceComplexities[i] = space + 2 * inputSize;

                // Calculate total collisions
                totalCollisions[i] = hash.getInnerCollisions() ;
            }

            // Write data to CSV file
            try {
                new CSVWriter().writeData("data".concat(String.valueOf(x).concat(".csv")), inputSize, sampleSize,
                        averageTimesPerInsertion,averageTimesPerSearch,totalCollisions, spaceComplexities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
