package com.example;

import java.util.ArrayList;

public class HashingN2<V> extends UniversalHashing2 {

    private V[] hashTable; // Array to store hash table entries
    private int countCollisions = 0; // Counter for collisions
    private int maxNumber; // Maximum number for hashing
    private int hashTableSize; // Size of the hash table
    private UniversalHashing2 newHash; // Instance of UniversalHashing2 for rehashing

    // Constructor
    public HashingN2(int maxNumber, int hashTableSize) {
        // Call superclass constructor with specified maximum number and hash table size
        super(maxNumber, hashTableSize * hashTableSize);
        this.maxNumber = maxNumber; // Set the maximum number
        this.hashTableSize = hashTableSize * hashTableSize; // Calculate the size of the hash table (power of 2)
        this.newHash = new UniversalHashing2(this.maxNumber, this.hashTableSize); // Initialize new hashing instance
        hashTable = (V[]) new Object[this.hashTableSize]; // Initialize the hash table array
    }

    // Method to rehash the hash table
    private void rehash() {
        V[] oldHashTable = hashTable.clone(); // Create a clone of the old hash table
        this.newHash = new UniversalHashing2(this.maxNumber, this.hashTableSize); // Create a new hashing instance
        hashTable = (V[]) new Object[this.hashTableSize]; // Initialize a new hash table array
        // Iterate over the entries in the old hash table
        for (V value : oldHashTable) {
            // If the entry is not null, insert it into the new hash table
            if (value != null) {
                insert(value);
            }
        }
    }

    // Method to insert a value into the hash table
    public void insert(V value) {
        int key = value.hashCode(); // Get the hash code of the value
        int[][] binaryOfKey = bitRepresentation(key); // Get the binary representation of the hash code
        int index = this.newHash.computeIndex(binaryOfKey); // Compute the index in the hash table
        // If the slot is empty, insert the value
        if (hashTable[index] == null) {
            hashTable[index] = value;
        } else {
            this.countCollisions++; // Increment collision counter
            rehash(); // Rehash the hash table
            insert(value); // Reattempt insertion after rehashing
        }
    }

    // Method to search for a value in the hash table
    public boolean search(V value) {
        int key = value.hashCode(); // Get the hash code of the value
        int[][] binaryOfKey = bitRepresentation(key); // Get the binary representation of the hash code
        int index = this.newHash.computeIndex(binaryOfKey); // Compute the index in the hash table
        // Return true if the value is found at the computed index, false otherwise
        return hashTable[index] != null && hashTable[index].equals(value);
    }

    // Method to delete a value from the hash table
    public void delete(V value) {
        // Check if the value exists in the hash table
        if(search(value)) {
            int key = value.hashCode(); // Get the hash code of the value
            int[][] binaryOfKey = bitRepresentation(key); // Get the binary representation of the hash code
            int index = this.newHash.computeIndex(binaryOfKey); // Compute the index in the hash table
            hashTable[index] = null; // Set the slot to null to delete the value
        }
        else {
            System.out.println("Value not found"); // Print a message if the value is not found
        }
    }

    // Method to display the hash table contents
    public void display() {
        // Iterate over the hash table array
        for (int i = 0; i < hashTable.length; i++) {
            // Print the index and value at each slot in the hash table
            System.out.println("Index: " + i + " Value: " + hashTable[i]);
        }
        // Print the total number of collisions
        System.out.println("Collisions: " + countCollisions);
    }
    public int getCountCollisions() {
        return countCollisions;
    }
}
