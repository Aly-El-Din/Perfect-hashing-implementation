package com.example;

import java.util.ArrayList;

public class HashingN2<V> extends UniversalHashing2 {

    private V[] hashTable;
    private V[] hashTable2;
    private int countCollisions = 0;
    private int maxNumber;
    private int hashTableSize;
    private UniversalHashing2 newHash;

    public HashingN2(int maxNumber, int hashTableSize) {
        super(maxNumber, hashTableSize * hashTableSize);
        this.maxNumber = maxNumber;
        this.hashTableSize = hashTableSize * hashTableSize; // size of the hash table power of 2
        this.newHash = new UniversalHashing2(this.maxNumber, this.hashTableSize);
        hashTable = (V[]) new Object[this.hashTableSize];
        hashTable2 = (V[]) new Object[this.hashTableSize];
    }

    private void rehash() {
        V[] oldHashTable = hashTable.clone();
        this.newHash = new UniversalHashing2(this.maxNumber, this.hashTableSize);
        hashTable = (V[]) new Object[this.hashTableSize];
        for (V value : oldHashTable) {
            if (value != null) {
                insert(value);
            }
        }
    }

    public void insert(V value) {
        int key = value.hashCode();
        int[][] binaryOfKey = bitRepresentation(key);
        int index = this.newHash.computeIndex(binaryOfKey);
        if (hashTable[index] == null) {
            hashTable[index] = value;
        } else {
            this.countCollisions++;
            rehash();
            insert(value);
        }
    }

    public boolean search(V value) {
        int key = value.hashCode();
        int[][] binaryOfKey = bitRepresentation(key);
        int index = this.newHash.computeIndex(binaryOfKey);
        return hashTable[index] != null && hashTable[index].equals(value);
    }

    public void delete(V value) {
        if(search(value)) {
            int key = value.hashCode();
            int[][] binaryOfKey = bitRepresentation(key);
            int index = this.newHash.computeIndex(binaryOfKey);
            hashTable[index] = null;
        }
        else {
            System.out.println("Value not found");
        }
    }

    public void display() {
        for (int i = 0; i < hashTable.length; i++) {
            
                System.out.println("Index: " + i + " Value: " + hashTable[i]);
            
        }
        System.out.println("Collisions: " + countCollisions);
    }
}
