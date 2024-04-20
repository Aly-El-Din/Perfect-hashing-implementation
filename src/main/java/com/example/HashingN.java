package com.example;

public class HashingN<V> {

    private UniversalHashing h1; // hash function of primary table
    private UniversalHashing[] h2; // array of hash functions of secondary tables associated with indcies in primary table
    int count; // number of key-value pairs in table
    private int M; // size of primary table (number of keys in primary table)
    private int[] N; // sizes of secondary tables
    private V[][] table; // primary table
    private int innerCollisions = 0 , outerCollisions = 0; // number of collisions
    private int[] counts; // sizes of secondary tables
    private V [] temp , auxiliaryTable;

    public HashingN(int M) {
        this.M = M;
        this.N = new int[M];
        this.h1 = new UniversalHashing(M);
        this.table = (V[][]) new Object[M][];
        this.h2 = new UniversalHashing[M];
        this.counts = new int[M];
        for (int i = 0; i < M; i++) {
            this.N[i] = 2;
            this.counts[i] = 0;
        }
    }

    /*
     * Insertion tested and working
     */
    @SuppressWarnings("unchecked")
    public boolean insert(V var) {
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);

        if (table[primaryIndex] == null) {
            // Initialize secondary hash table
            h2[primaryIndex] = new UniversalHashing(N[primaryIndex]);
            V[] secondaryTable = (V[]) new Object[N[primaryIndex]];
            int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
            secondaryTable[secondaryIndex] = var;
            table[primaryIndex] = secondaryTable;
        } else {
            int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
            if (table[primaryIndex][secondaryIndex] == null) {
                outerCollisions++;
                table[primaryIndex][secondaryIndex] = var;
            } else {
                innerCollisions++;
                //Collision handling
                int currIndex = 0;
                temp = (V[]) new Object[counts[primaryIndex] + 1];
                for (int i = 0; i < table[primaryIndex].length; i++) {
                    if (table[primaryIndex][i] != null)
                        temp[currIndex++] = table[primaryIndex][i];
                }
                temp[currIndex] = var;
                while (true) {
                    if (rehash(primaryIndex)) {
                        table[primaryIndex] = auxiliaryTable;
                        break;
                    }
                }
            }
        }
        counts[primaryIndex]++;
        count++;
        return search(var);
    }

    @SuppressWarnings("unchecked")
    private boolean rehash(int primaryIndex) {
        int innerIndex;
        int oldSize = N[primaryIndex];
        int newSize = oldSize * 2;
        N[primaryIndex] = newSize;
        h2[primaryIndex] = new UniversalHashing(newSize);
        auxiliaryTable = (V[]) new Object[newSize];

        for (V v : temp) {
            if (v == null) {
                continue;
            }
            System.out.println(newSize);
            innerIndex = h2[primaryIndex].computeIndex(hash(v, newSize));
            if ((auxiliaryTable[innerIndex] != null)) {
                return false;
            } else {
                auxiliaryTable[innerIndex] = v;
            }
        }
        return true;
    }

     private int hash(V var, int M) {
         return (var.hashCode() & Integer.MAX_VALUE) % M;
     }

    /**
     * Delete not tested
     */
    public void delete(V var) {
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);
        int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
        if (table[primaryIndex][secondaryIndex] != null) {
            table[primaryIndex][secondaryIndex] = null;
            counts[primaryIndex]--;
        }
    }
    /*
    * Search tested and working
    */
    public boolean search(V var) {

        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);
        int secondaryIndex = h2[primaryIndex].computeIndex(hash(var,N[primaryIndex]));

        return table[primaryIndex][secondaryIndex].equals(var);
    }

    public int getInnerCollisions() {
        return innerCollisions;
    }
    public int getOuterCollisions() {
        return outerCollisions;
    }

    public int review(){
        int sum = 0;
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                for (int j = 0; j < table[i].length; j++) {
                    if (table[i][j] != null) {
                        sum++;
                    }
                }
            }
        }
        return sum;
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
        System.out.println( "Count: " + count);
    }
    


}
