package com.example.HashingAlgorithm;

import java.util.Random;

public class UniversalHashing<V> {
    private  int bitsOfMaxNumber;
    private final int bits_indices;
    private int[][] h;

    public UniversalHashing(boolean isString, long maxNumber, int hashTableSize) {
        if (isString) {
            this.bitsOfMaxNumber = 80; // 80 bits for a string of 8 bytes * 10 characters
        } else {
            this.bitsOfMaxNumber = (int) (Math.log(maxNumber) / Math.log(2)) + 1;
        }
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
    public int computeIndexString(String s) {
        byte[] input = s.getBytes();
        int [] E = byteArrayToBitArray(input);
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

    public  int[] byteArrayToBitArray(byte[] byteArray) {
        int[] bitArray = new int[byteArray.length * 8]; // Each byte has 8 bis

        this.setbitsOfMaxNumber(byteArray.length * 8); //set the length of the bit array that used to store the bits of the byte array before multiplication
        
        int index = 0;
        for (byte b : byteArray) {
            for (int j = 7; j >= 0; j--) {
                // Extracting individual bits using bitwise AND with 1 shifted j positions
                bitArray[index++] = (b >> j) & 1;
            }
        }
        
        return bitArray;
    }
    public void setbitsOfMaxNumber(int bitsOfMaxNumber) {
        this.bitsOfMaxNumber = bitsOfMaxNumber;
    }
}
