
package com.example;

class Generator {

    public static void main(String[] args) {
        // unique random string genrator
      Generator Generator = new Generator();
        String[] strings = new String[100];
        for (int i = 0; i < 100; i++) {
            strings[i] = Generator.generateString();
        }
        for (String s : strings) {
            System.out.println(s);
        }
    }
    
    public static  String generateString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}