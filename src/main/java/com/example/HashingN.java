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



    private int duplicateCount=0;

    public HashingN(int sizeOfPrimaryTable) {
        this.M = sizeOfPrimaryTable;
        this.N = new int[M];
        this.h1 = new UniversalHashing(Integer.MAX_VALUE,M);
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
    public String insert(V var) {
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);

        if (table[primaryIndex] == null) {
            // Initialize secondary hash table
            h2[primaryIndex] = new UniversalHashing(Integer.MAX_VALUE,N[primaryIndex]);
            V[] secondaryTable = (V[]) new Object[N[primaryIndex]];
            int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
            secondaryTable[secondaryIndex] = var;
            table[primaryIndex] = secondaryTable;
        } else {
            //secondary table already exists with at least one element
            int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
            if (table[primaryIndex][secondaryIndex] == null) {
                outerCollisions++;
                table[primaryIndex][secondaryIndex] = var;
            } else {
                //possible collision or previous hash same element
                if(table[primaryIndex][secondaryIndex].equals(var)){
                    this.duplicateCount++;
                    return "Already exists in the table";
                }
                else {
                    innerCollisions++;
                    //Collision handling
                    //System.out.println("Collision at Secondary index: " + secondaryIndex + " in primary index: " + primaryIndex + " for value: " + var );
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
        }
        counts[primaryIndex]++;
        count++;
        if (search(var)){
            return "Inserted successfully";
        }
        return "Insertion failed";
    }

    @SuppressWarnings("unchecked")
    private boolean rehash(int primaryIndex) {
        int innerIndex;
        int oldSize = N[primaryIndex];
        int newSize = oldSize * 2;
        N[primaryIndex] = newSize;
        h2[primaryIndex] = new UniversalHashing(Integer.MAX_VALUE,newSize);
        auxiliaryTable = (V[]) new Object[newSize];

        for (V v : temp) {
            if (v == null) {
                continue;
            }
            //System.out.println(newSize);
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
    public String delete(V var) {
        if(!search(var)){
            return "Element not found";
        }
        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);
        int secondaryIndex = h2[primaryIndex].computeIndex(hash(var, N[primaryIndex]));
        if (table[primaryIndex][secondaryIndex] != null) {
            table[primaryIndex][secondaryIndex] = null;
            counts[primaryIndex]--;
        }
        if(search(var)){
            return "Deletion failed";
        }
        return "Deleted successfully";
    }
    /*
    * Search tested and working
    */
    public boolean search(V var) {

        int digest = hash(var, M);
        int primaryIndex = h1.computeIndex(digest);
        if (table[primaryIndex] == null) {
            return false;
        }
        //check existance of secondary table
        if(h2[primaryIndex] == null){
            return false;
        }
        int secondaryIndex = h2[primaryIndex].computeIndex(hash(var,N[primaryIndex]));
        if(table[primaryIndex][secondaryIndex] == null){
            return false;
        }
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
    public int getDuplicateCount() {
        return duplicateCount;
    }
    


}
