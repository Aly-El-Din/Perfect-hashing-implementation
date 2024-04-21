package com.example;

public class HashingN2<V> extends UniversalHashing2 {

    private V[] hashTable;
    private int countCollisions = 0;
    private long maxNumber;
    private int hashTableSize;
    private int primarySize;
    private UniversalHashing2 newHash;

    private int duplicateCount = 0;

    public HashingN2(long maxNumber, int hashTableSize) {
        super(maxNumber, hashTableSize * hashTableSize);
        this.maxNumber = maxNumber;
        this.primarySize = hashTableSize;
        this.hashTableSize = hashTableSize * hashTableSize; // size of the hash table power of 2
        this.newHash = new UniversalHashing2(this.maxNumber, this.hashTableSize);
        hashTable = (V[]) new Object[this.hashTableSize];
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

    public String insert(V value) {
        int key = ((value.hashCode()) & Integer.MAX_VALUE) % this.hashTableSize;
        int[][] binaryOfKey = bitRepresentation(key);
        int index = this.newHash.computeIndex(binaryOfKey);

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
                rehash(); // Rehashing to resolve collision
                insert(value); // Attempt to insert again after rehashing
                // Since the insertion is retried after rehashing, no need to return here
            }
        }

        // If the value is found after insertion or rehashing, return success message
        if (search(value)) {
           return "Inserted successfully";
        }

        // If insertion fails for any reason, return failure message
        return "Insertion failed";
    }


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

            int key = value.hashCode();
            int[][] binaryOfKey = bitRepresentation(key);
            int index = this.newHash.computeIndex(binaryOfKey);
            hashTable[index] = null;
        if(search(value)){
            return "Deletion failed";
        }
        return "Deleted successfully";


    }

    public void display() {
        for (int i = 0; i < hashTable.length; i++) {
            
                System.out.println("Index: " + i + " Value: " + hashTable[i]);
            
        }
        System.out.println("Collisions: " + countCollisions);
    }
    public int getDuplicateCount() {
        return duplicateCount;
    }
}
