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
//
//        HashingN<String> hash2 = new HashingN<>(10);
//        System.out.println(hash2.insert("Amir"));
//        System.out.println(hash2.insert("Ali"));
//        System.out.println(hash2.insert("Abdallah"));
//        System.out.println(hash2.insert("Amir"));
//        System.out.println(hash2.search("Ali"));
//        System.out.println(hash2.search("Amir"));
//        System.out.println(hash2.search("A"));
//        System.out.println(hash2.delete("Ali"));
//        System.out.println(hash2.search("Ali"));
//        System.out.println(hash2.delete("Axx"));
//        System.out.println(hash2.search("Axx"));
//        System.out.println(hash2.search("Axx"));
//        Dictionary dictionary = new DictionaryNSpace(10);
//
//        String path = "C:\\Users\\Amir\\OneDrive - Alexandria University\\Desktop\\FOE-AU\\CSED_Y2_2nd Semester\\Data Structures & Algorithms\\Labs\\Lab-2  Hashing\\Perfect-hashing-implementation\\Words.txt";
//        dictionary.batchInsert(path);
//        //dictionary.batchInsert("\"C:\\Users\\Amir\\OneDrive - Alexandria University\\Desktop\\FOE-AU\\CSED_Y2_2nd Semester\\Data Structures & Algorithms\\Labs\\Lab-2  Hashing\\Perfect-hashing-implementation\\Words.txt\"");
//        System.out.println(dictionary.search("Axx"));
//        System.out.println(dictionary.search("Ali"));
//        System.out.println(dictionary.search("Amir"));
//        System.out.println(dictionary.search("Ass"));
//        System.out.println(dictionary.search("Ebrahim"));
//        Dictionary dic = new DictionaryN2Space(2);
//        System.out.println(dic.insert("A"));
//        System.out.println(dic.insert("sdd"));
//        System.out.println(dic.insert("sdfff"));
//        System.out.println(dic.insert("sdtrghyjjd"));;
    //HashingN2<String> hash = new HashingN2<>(Long.MAX_VALUE,2);

//        String value = "Ebrahim";
//        System.out.println((Math.abs(value.hashCode()) & Integer.MAX_VALUE) % 4);
//        System.out.println((Math.abs(value.hashCode()) & Integer.MAX_VALUE) );
//        hash.insert("Ebrahim");
//        hash.insert("Amir");
//        hash.insert("Hassan");
//        hash.insert("jemssjsdh");
//        hash.display();
        DictionaryN2Space hash = new DictionaryN2Space(2);
        System.out.println(hash.insert("Ebrahim"));
        System.out.println(hash.insert("Amir"));
        System.out.println(hash.insert("Hassan"));
        System.out.println(hash.insert("jemssjsdh"));
        hash.display();







    }
}
