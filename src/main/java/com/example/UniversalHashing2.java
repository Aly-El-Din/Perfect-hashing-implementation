package com.example;
import java.util.*;
import com.example.BitwiseMatrixMultiplication;

public class UniversalHashing2 {
    
    // Instance variables
    protected ArrayList<ArrayList<Integer>> hashTable; // Hash table
    private int M; // Size of the hash table
    private int u; // Number of bits for the maximum number
    private int b; // Number of bits for the size of the hash table
    private int[][] hashFunctions; // Array of hash functions

    // Constructor
    public UniversalHashing2(int maxNumber, int hashTableSize) {
        // Calculate the number of bits for the maximum number
        this.u = (int) (Math.log(maxNumber) / Math.log(2) + 1);
        this.M = hashTableSize; // Set the size of the hash table
        // Calculate the number of bits for the size of the hash table
        this.b = (int) (Math.log(hashTableSize) / Math.log(2));
        // Initialize the array to store hash functions
        this.hashFunctions = new int[b][u];
        // Generate hash functions
        matrixConstruction();
    }    

    // Method to generate random hash functions
    private void matrixConstruction() {
        // Loop through each hash function
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < u; j++) {
                // Generate a random binary value (0 or 1) for each bit of the hash function
                hashFunctions[i][j] = new Random().nextInt(2);
            }
        }
    } 
    
    // Method to convert a key into its binary representation
    protected int[][] bitRepresentation(int key) {
        // Initialize a 2D array to store the binary representation of the key
        int[][] binary = new int[u][1];
        int i = 0;
        // Convert the key to binary
        while (key > 0) {
            // Store the remainder (binary digit) in the array
            binary[i][0] = key % 2;
            // Divide the key by 2 to shift to the next bit
            key /= 2;
            // Move to the next bit in the array
            i++;
        }
        return binary;
    }

    // Method to retrieve the hash functions
    protected int[][] getHashFunctions() {
        return hashFunctions;
    }

    // Method to compute the index in the hash table using bitwise matrix multiplication
    protected int computeIndex(int[][] binaryOfKey) {
        int index = 0;
        // Initialize the array to store the result of matrix multiplication
        int[][] bitIndex = new int[b][1];
        // Create an instance of the BitwiseMatrixMultiplication class
        BitwiseMatrixMultiplication m = new BitwiseMatrixMultiplication();
        // Set the matrices for multiplication
        m.setMatrix1(hashFunctions);
        m.setMatrix2(binaryOfKey);
        // Perform the matrix multiplication
        m.multiply();
        // Get the result of multiplication
        bitIndex = m.getResult();
        // Compute the index from the result
        for (int i = 0; i < b; i++) {
            index += bitIndex[i][0] * Math.pow(2, i);
        }
        return index;
    }

    // Getter and setter methods for instance variables
    protected ArrayList<ArrayList<Integer>> getHashTable() {
        return hashTable;
    }

    public void setHashTable(ArrayList<ArrayList<Integer>> hashTable) {
        this.hashTable = hashTable;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setHashFunctions(int[][] hashFunctions) {
        this.hashFunctions = hashFunctions;
    }
}
