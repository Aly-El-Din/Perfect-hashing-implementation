package com.example;

public class HashingN2<V> {
    private Boolean isString;
    private V[] hashTable;
    private int countCollisions = 0;
    private long maxNumber;
    private int hashTableSize;
    private int primarySize;
    private UniversalHashing newHash;
    int count = 0;
    private int duplicateCount = 0;



    private int expectedNumberOfElements = 0;

    public HashingN2(boolean isString,long maxNumber, int hashTableSize) {
        this.isString = isString;
        this.maxNumber = maxNumber; // Maximum number of elements
        this.expectedNumberOfElements = hashTableSize; // Expected number of elements
        this.primarySize = hashTableSize; // Size of the primary table
        this.hashTableSize = hashTableSize * hashTableSize; // size of the hash table power of 2
        this.newHash = new UniversalHashing(isString,this.maxNumber, this.hashTableSize);
        hashTable = (V[]) new Object[this.hashTableSize];
    }

    // Method to rehash the hash table
    public void rehash(int newSize) {
        V[] oldHashTable = hashTable.clone(); // Create a clone of the old hash table
        this.expectedNumberOfElements = newSize; // Update the expected number of elements
        this.hashTableSize = newSize * newSize; // Update the hash table size
        this.newHash = new UniversalHashing(isString ,this.maxNumber, this.hashTableSize); // Create a new hashing instance
        hashTable = (V[]) new Object[this.hashTableSize]; // Initialize a new hash table array
        // Iterate over the entries in the old hash table
        for (V value : oldHashTable) {
            // If the entry is not null, insert it into the new hash table
            if (value != null) {
                insert(value);
            }
        }
    }

    public String insert(V value) {
        int index;
        if (this.isString){
            index = this.newHash.computeIndexString(value.toString());    
        }
        else{
        index = this.newHash.computeIndex(Math.abs((int)value)); 
        }
        if (hashTable[index] == null) {
            // Insert if the slot is empty
            hashTable[index] = value;
            //return "Inserted successfully";
        } else {
            // Handle collision or repetition
            if (hashTable[index].equals(value)) {
                this.duplicateCount++;
                return "Already exists in the table";
            } else {
                // Collision occurred
                System.out.println("Collision at index: " + index + " Value: " + hashTable[index] + " New Value: " + value);
                this.countCollisions++;
                rehash(this.expectedNumberOfElements+1); // Rehashing to resolve collision
                insert(value); // Attempt to insert again after rehashing
                // Since the insertion is retried after rehashing, no need to return here
            }
        }

        // If the value is found after insertion or rehashing, return success message
        if (search(value)) {
            return "Inserted successfully";
        }
        // If insertion fails for any reason, return failure message
          throw new RuntimeException("Insertion failed");
    }


    public boolean search(V value) {
        int index;
        if (this.isString){
            index = this.newHash.computeIndexString(value.toString());    
        }
        else{
        index = this.newHash.computeIndex(Math.abs((int)value)); 
        }
        return hashTable[index] != null && hashTable[index].equals(value);
    }

    public String delete(V value) {
        if(!search(value)){
            return "Element not found";
        }
        int index;
        if (this.isString){
            index = this.newHash.computeIndexString(value.toString());    
        }
        else{
        index = this.newHash.computeIndex(Math.abs((int)value)); 
        }
            hashTable[index] = null;
        if(search(value)){
           throw new RuntimeException("Deletion failed");
        }
        return "Deleted successfully";


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
        System.out.println("Duplicates: " + duplicateCount);
    }

    public int getDuplicateCount() {
        return duplicateCount;
    }

    public int getCollisionCount() {
        return countCollisions;
    }
    

    public int getCount() {
        int temp = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                temp++;
            }
        }
        return temp;
    }

    public int getExpectedNumberOfElements() {
        return expectedNumberOfElements;
    }
    
    public int getHashTableSize() {
        return hashTableSize;
    }
}
