package com.example;

public class HashingN2<V> extends UniversalHashing2 {

<<<<<<< HEAD
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
=======
    private V[] hashTable;
    private int countCollisions = 0;
    private long maxNumber;
    private int hashTableSize;
    private int primarySize;
    private UniversalHashing2 newHash;
    int count = 0;

    private int duplicateCount = 0;

    public HashingN2(long maxNumber, int hashTableSize) {
        super(maxNumber, hashTableSize * hashTableSize);
        this.maxNumber = maxNumber;
        this.primarySize = hashTableSize;
        this.hashTableSize = hashTableSize * hashTableSize; // size of the hash table power of 2
        this.newHash = new UniversalHashing2(this.maxNumber, this.hashTableSize);
        hashTable = (V[]) new Object[this.hashTableSize];
>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
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

<<<<<<< HEAD
    // Method to insert a value into the hash table
    public void insert(V value) {
        int key = value.hashCode(); // Get the hash code of the value
        int[][] binaryOfKey = bitRepresentation(key); // Get the binary representation of the hash code
        int index = this.newHash.computeIndex(binaryOfKey); // Compute the index in the hash table
        // If the slot is empty, insert the value
=======
    public String insert(V value) {
        int key = ((value.hashCode()) & Integer.MAX_VALUE) % this.hashTableSize;
        int[][] binaryOfKey = bitRepresentation(key);
        int index = this.newHash.computeIndex(binaryOfKey);

>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
        if (hashTable[index] == null) {
            // Insert if the slot is empty
            hashTable[index] = value;
            //return "Inserted successfully";
        } else {
<<<<<<< HEAD
            this.countCollisions++; // Increment collision counter
            rehash(); // Rehash the hash table
            insert(value); // Reattempt insertion after rehashing
=======
            // Handle collision or repetition
            if (hashTable[index].equals(value)) {
                this.duplicateCount++;
                return "Already exists in the table";
            } else {
                // Collision occurred
                System.out.println("Collision at index: " + index + " Value: " + hashTable[index] + " New Value: " + value);
                this.countCollisions++;
                rehash(); // Rehashing to resolve collision
                insert(value); // Attempt to insert again after rehashing
                // Since the insertion is retried after rehashing, no need to return here
            }
>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
        }

        // If the value is found after insertion or rehashing, return success message
        if (search(value)) {
            return "Inserted successfully";
        }
        // If insertion fails for any reason, return failure message
          throw new RuntimeException("Insertion failed");
    }

<<<<<<< HEAD
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
=======

    public boolean search(V value) {
        int key = ((value.hashCode()) & Integer.MAX_VALUE) % this.hashTableSize;
        int[][] binaryOfKey = bitRepresentation(key);
        int index = this.newHash.computeIndex(binaryOfKey);
        return hashTable[index] != null && hashTable[index].equals(value);
    }

    public String delete(V value) {
        if(!search(value)){
            return "Element not found";
        }
            int key = ((value.hashCode()) & Integer.MAX_VALUE) % this.hashTableSize;
            int[][] binaryOfKey = bitRepresentation(key);
            int index = this.newHash.computeIndex(binaryOfKey);
            hashTable[index] = null;
        if(search(value)){
           throw new RuntimeException("Deletion failed");
        }
        return "Deleted successfully";


>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
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
<<<<<<< HEAD
    public int getCountCollisions() {
        return countCollisions;
=======

    public int getDuplicateCount() {
        return duplicateCount;
    }
    

    public int getCount() {
        int temp = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                temp++;
            }
        }
        return temp;
>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
    }
}
