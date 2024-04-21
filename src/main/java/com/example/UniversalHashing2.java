package com.example;
import java.util.*;
import com.example.BitwiseMatrixMultiplication;

public class UniversalHashing2 {
    
    protected ArrayList<ArrayList<Integer>> hashTable;
    private int M; // size of the hash table
    private int u; // number of bits for the max number
    private int b; // number of bits for the size of the hash table
    private int[][] hashFunctions;

    public UniversalHashing2(long maxNumber, int hashTableSize) {
        this.u = (int) (Math.log(maxNumber) / Math.log(2) + 1);
        this.M = hashTableSize;
        this.b = (int) (Math.log(hashTableSize) / Math.log(2));
        this.hashFunctions = new int[b][u];
        matrixConnstruction();
    }    

    private void matrixConnstruction() {
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < u; j++) {
                hashFunctions[i][j] = new Random().nextInt(2);
            }
        }
    }
    
    
    protected int[][] bitRepresentation(int key) {
        int[][] binary = new int[u][1];
        int i = 0;
        while (key > 0) {
            if(key == 1){
                binary[i][0] = 1;
                break;
            }
            binary[i][0] = key % 2;
            key /= 2;
            i++;
        }
        return binary;
    }

    protected int[][] getHashFunctions() {
        return hashFunctions;
    }

    protected int computeIndex(int[][] binaryOfKey) {
        int index = 0;
        int[][] bitIndex = new int[b][1]; // Initialize the second dimension of the array
        BitwiseMatrixMultiplication m = new BitwiseMatrixMultiplication();
        m.setMatrix1(hashFunctions);
        m.setMatrix2(binaryOfKey);
        m.multiply();
        bitIndex = m.getResult();
        for (int i = 0; i < b; i++) {
            index += bitIndex[i][0] * Math.pow(2, i);
        }
        return index;
    }
    
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

   

    public void setHashFunctions(int [][] hashFunctions) {
        this.hashFunctions = hashFunctions;
    }
    

}
