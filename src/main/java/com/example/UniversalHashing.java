package com.example;

import java.util.ArrayList;
import java.util.Random;

public class UniversalHashing {
    private final int bitsOfmaxNumber;
    private final int bits_indices;
    private ArrayList<ArrayList<Integer>> h;

    public UniversalHashing(int maxNumber,int hashTableSize) {
        this.bitsOfmaxNumber = (int) (Math.log(maxNumber) / Math.log(2) + 1);
        // Size of the hash table/ number of buckets
        this.bits_indices = (int) (Math.log(hashTableSize) / Math.log(2)); // i = log2(M)
        h = new ArrayList<>();
        randomizeMatrix();
    }

    public int computeIndex(int input) {
        ArrayList<Integer> E = getBinaryKey(input);
        ArrayList<Integer> index = matrixMultiplication(E);
        return convertToDecimal(index);
    }

    private void randomizeMatrix() {
        int rows = bits_indices;
        int cols = bitsOfmaxNumber;
        for (int i = 0; i < rows; i++) {
            ArrayList<Integer> innerList = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                int randomBinary = new Random().nextInt(2);
                innerList.add(randomBinary);
            }
            h.add(innerList);
        }
    }

    private ArrayList<Integer> getBinaryKey(int key) {
        ArrayList<Integer> binary = new ArrayList<>();
        for (int i = 0; i < bitsOfmaxNumber; i++) {
            binary.add(0);
        }
        int i = 0;
        while (key > 0) {
            binary.add(i, key % 2);
            key /= 2;
            i++;
        }
        return binary;
    }

    private ArrayList<Integer> matrixMultiplication(ArrayList<Integer> binaryKey) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < bits_indices; i++) {
            int element = 0;
            for (int j = 0; j < bitsOfmaxNumber; j++) {
                element += h.get(i).get(j) * binaryKey.get(j);
            }
            index.add(element % 2);
        }
        return index;
    }

    private int convertToDecimal(ArrayList<Integer> binaryArray) {
        int decimal = 0;
        for (int i = 0; i < binaryArray.size(); i++) {
            int power = binaryArray.size() - 1 - i; // Calculate the power of 2 for the current bit
            decimal += binaryArray.get(i) * Math.pow(2, power);
        }
        return decimal;
    }
}