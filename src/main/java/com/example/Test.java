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
//        HashingN<Integer> hash = new HashingN<>(2);
//        System.out.println(hash.insert(5));
//        System.out.println(hash.insert(7));
//        System.out.println(hash.insert(1));
//        System.out.println(hash.insert(5));

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
        System.out.println(hash2.search("Axx"));
        System.out.println(hash2.search("Axx"));
        Dictionary dictionary = new DictionaryNSpace(10);

        String path = "C:\\Users\\Amir\\OneDrive - Alexandria University\\Desktop\\FOE-AU\\CSED_Y2_2nd Semester\\Data Structures & Algorithms\\Labs\\Lab-2  Hashing\\Perfect-hashing-implementation\\Words.txt";
        dictionary.batchInsert(path);
        //dictionary.batchInsert("\"C:\\Users\\Amir\\OneDrive - Alexandria University\\Desktop\\FOE-AU\\CSED_Y2_2nd Semester\\Data Structures & Algorithms\\Labs\\Lab-2  Hashing\\Perfect-hashing-implementation\\Words.txt\"");
        System.out.println(dictionary.search("Axx"));
        System.out.println(dictionary.search("Ali"));
        System.out.println(dictionary.search("Amir"));
        System.out.println(dictionary.search("Ass"));
        System.out.println(dictionary.search("Ebrahim"));




    }
}
