package com.example;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import org.junit.Test;

import java.util.Random;

public class SamplingAndComparisonTest {

    // samlping data about space and time complexity of HashingN
    @Test
    public void complexityAnalysisTest() {
        int space = 0; // space complexity
        long totalTime = 0; // total time taken to insert entries
        int sampleSize = 5; // number of samples to be taken of a given input size
        int inputSize = 1000; // random input size (size of entries to be inserted)
        int c = new Random().nextInt(9) + 1; // random constant c assumed to be between 1 - 9
        int maxInputSize = 10001; // maximum input size
        int[] spaceComplexities = new int[sampleSize];
        long[] averageTimesPerInsertion = new long[sampleSize];

        for (int x = inputSize; x < maxInputSize; x += 1000) {

            for (int i = 0; i < sampleSize; i++) {
                HashingN<String> hash = new HashingN<>(2 * inputSize);
                long start = System.currentTimeMillis();

                for (int j = 0; j < x; j++)
                    hash.insert(new Generator().generateString());

                long end = System.currentTimeMillis();

                totalTime = (end - start);

                int[] N = hash.getN();
                for (int k = 0; k < 2 * inputSize; k++) {
                    space += N[k];
                } 

                // Calculate average time per insertion
                averageTimesPerInsertion[i] = totalTime;

                // Calculate space complexity
                
                spaceComplexities[i] = space + 2 * inputSize;
            }

            // Write data to CSV file
            try {
                new CSVWriter().writeData("data".concat(String.valueOf(x).concat(".csv")), inputSize, sampleSize,
                        averageTimesPerInsertion, spaceComplexities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
