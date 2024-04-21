package com.example;

public class App {
    public static void main(String[] args) {
        HashingN2<String> h = new HashingN2<>(Integer.MAX_VALUE, 2);
        h.insert("Amir");
        h.insert("Ebrahim");
        h.insert("Ali");
        h.insert("Abdallah");
        h.display();
        h.delete("Amir");
        h.display();
    }
    
}
