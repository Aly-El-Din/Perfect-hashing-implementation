package com.example;

import java.util.Random;

public class UniversalHashing {
    private final int bitsOfMaxNumber;
    private final int bits_indices;
    private int[][] h;

    public UniversalHashing(int maxNumber, int hashTableSize) {
        this.bitsOfMaxNumber = (int) (Math.log(maxNumber) / Math.log(2) + 1);
        // Size of the hash table/ number of buckets
        this.bits_indices = (int) (Math.log(hashTableSize) / Math.log(2)); // i = log2(M)
        h = new int[bits_indices][bitsOfMaxNumber];
        randomizeMatrix();
    }

    public int computeIndex(int input) {
        int[] E = getBinaryKey(input);
        int[] index = matrixMultiplication(E);
        return convertToDecimal(index);
    }

    private void randomizeMatrix() {
        for (int i = 0; i < bits_indices; i++) {
            for (int j = 0; j < bitsOfMaxNumber; j++) {
                h[i][j] = new Random().nextInt(2);
            }
        }
    }

    private int[] getBinaryKey(int key) {
        int[] binary = new int[bitsOfMaxNumber];
        for (int i = 0; i < bitsOfMaxNumber; i++) {
            binary[i] = 0;
        }
        int i = 0;
        while (key > 0) {
            binary[i] = key % 2;
            key /= 2;
            i++;
        }
        return binary;
    }

    private int[] matrixMultiplication(int[] binaryKey) {
        int[] index = new int[bits_indices];
        for (int i = 0; i < bits_indices; i++) {
            int element = 0;
            for (int j = 0; j < bitsOfMaxNumber; j++) {
                element ^= h[i][j] & binaryKey[j];
            }
            index[i] = element % 2;
        }
        return index;
    }

    private int convertToDecimal(int[] binaryArray) {
        int decimal = 0;
        for (int i = 0; i < binaryArray.length; i++) {
            int power = binaryArray.length - 1 - i; // Calculate the power of 2 for the current bit
            decimal += binaryArray[i] * Math.pow(2, power);
        }
        return decimal;
    }
}
