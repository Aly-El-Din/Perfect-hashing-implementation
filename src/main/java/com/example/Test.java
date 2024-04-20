package com.example;
public class Test {
    public static void main(String[] args) {
/*
        UniversalHashing test = new UniversalHashing(7,2);
        System.out.println(test.computeIndex(5));
        System.out.println(test.computeIndex(7));
        System.out.println(test.computeIndex(1));
        System.out.println(test.computeIndex(5));

 */
        HashingN<Integer> hash = new HashingN<>(2);
        System.out.println(hash.insert(5));
        System.out.println(hash.insert(7));
        System.out.println(hash.insert(1));
        System.out.println(hash.insert(5));

        HashingN<String> hash2 = new HashingN<>(10);
        System.out.println(hash2.insert("Amir"));
        System.out.println(hash2.insert("Ali"));
        System.out.println(hash2.insert("Abdallah"));
        System.out.println(hash2.insert("Amir"));
        System.out.println(hash2.search("Ali"));
        System.out.println(hash2.search("Amir"));
        System.out.println(hash2.search("A"));
        System.out.println(hash2.delete("Ali"));
        System.out.println(hash2.search("Ali"));
        System.out.println(hash2.delete("Axx"));


    }
}
