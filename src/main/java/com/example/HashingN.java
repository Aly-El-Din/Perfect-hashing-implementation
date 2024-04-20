package com.example;

public class HashingN<V> {

    UniversalHashing h1; // hash function of primary table
    UniversalHashing[] h2; // array of hash functions of secondary tables associated with indcies in primary table
    int count; // number of key-value pairs in table
    int M; // size of primary table (number of keys in primary table)
    int[] N; // sizes of secondary tables
    V[][] table; // primary table
    int innerCollisions = 0; // number of collisions in primary table
    int outerCollisions = 0; // number of collisions in secondary tables

    public HashingN(int primaryTableSize) {
        this.M = primaryTableSize;
        this.N = new int[M];
        this.h1 = new UniversalHashing(Integer.MAX_VALUE,M);
        this.table = (V[][]) new Object[M][];
        this.h2 = new UniversalHashing[M];
    }

    @SuppressWarnings("unchecked")
    /* public V insert(V var) {
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);
    
        if (table[primaryIndex] == null) {
    
            h2[primaryIndex] = new UniversalHashing(2);
            int secondaryIndex = h2[primaryIndex].computeIndex(digest);
    
            V[] secondaryTable = (V[]) new Object[2];
            secondaryTable[secondaryIndex] = var;
            table[primaryIndex] = secondaryTable;
            } else {
    
                int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
                
                // extend the secondary hash table
                V[] secondaryTable ;
                if (N[primaryIndex] == 1) 
                    secondaryTable = (V[]) new Object[2 * N[primaryIndex] * N[primaryIndex]];
                else
                    secondaryTable = (V[]) new Object[2 * N[primaryIndex]];
    
                for (int i = 0; i < table[primaryIndex].length +1; i++) {
                    secondaryTable[i] = table[primaryIndex][i];
                }
                table[primaryIndex] = secondaryTable;
    
                // check if collision exists
                if (table[primaryIndex][secondaryIndex] == null) {
                    table[primaryIndex][secondaryIndex] = var;
                } else {
                    // Rehash
                    rehash(primaryIndex, digest);
                    insert(var);
                }
            }
        N[primaryIndex]++;
        return var;
    }
    
    @SuppressWarnings("unchecked")
    private void rehash(int primaryIndex,int digest) {
    
        int newSize = N[primaryIndex] * N[primaryIndex] * 2;
        h2[primaryIndex] = new UniversalHashing(newSize);
        V[] secondaryTable = (V[]) new Object[newSize];
    
        for (int i = 0; i < table[primaryIndex].length; i++) {
            if (table[primaryIndex][i] != null) {
                int newIndex = h2[primaryIndex].computeIndex(digest);
                secondaryTable[newIndex] = table[primaryIndex][i];
            }
        }
        table[primaryIndex] = secondaryTable;
    }
    
    private int hash(V var, int M) {
        return (var.hashCode() & Integer.MAX_VALUE) % M;
    } */
    
    public boolean insert(V var) {
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);

        if (table[primaryIndex] == null) {
            // Initialize secondary hash table
            int secondarySize = 2; // Initial size, adjust as needed
            h2[primaryIndex] = new UniversalHashing(Integer.MAX_VALUE,secondarySize);
            V[] secondaryTable = (V[]) new Object[secondarySize];
            int secondaryIndex = h2[primaryIndex].computeIndex(digest);
            secondaryTable[secondaryIndex] = var;
            table[primaryIndex] = secondaryTable;
        } else {
            int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
            if (table[primaryIndex][secondaryIndex] == null) {
                table[primaryIndex][secondaryIndex] = var;
            } else {
                // Collision handling
                rehash(primaryIndex);
                return insert(var); // Recursively try to insert
            }
        }
        N[primaryIndex]++;
        return true;
    }

    private void rehash(int primaryIndex) {
        int oldSize = table[primaryIndex].length;
        int newSize = oldSize * 2; // Resize to double the size
        h2[primaryIndex] = new UniversalHashing(Integer.MAX_VALUE,newSize);
        V[] secondaryTable = (V[]) new Object[newSize];

        for (V item : table[primaryIndex]) {
            if (item != null) {
                int digest = hash(item, newSize);
                int newIndex = h2[primaryIndex].computeIndex(digest);
                secondaryTable[newIndex] = item;
            }
        }
        table[primaryIndex] = secondaryTable;
    }

    private int hash(V var, int M) {
        return (var.hashCode() & Integer.MAX_VALUE) % M;
    }

   
    
    public void print() {
        for (int i = 0; i < M; i++) {
            System.out.print(i + ": ");
            if (table[i] != null) {
                for (int j = 0; j < table[i].length; j++) {
                    if (table[i][j] != null) {
                        System.out.print(table[i][j] + " ");
                    }
                }
            }
            System.out.println();
        }
    }
    
    public void delete(V var) {
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);
        int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
        if (table[primaryIndex][secondaryIndex] != null) {
            table[primaryIndex][secondaryIndex] = null;
            N[primaryIndex]--;
        }
    }
    
    public boolean search(V var) {
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);

        if (table[primaryIndex] != null) {
            for (V item : table[primaryIndex]) {
                if (item != null && item.equals(var)) {
                    return true; 
                }
            }
        }
        return false; 
    }
    


}
