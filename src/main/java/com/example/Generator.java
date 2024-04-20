
package com.example;

import java.util.Random;

class Generator {

    public static void main(String[] args) {
        // unique random string genrator
        Generator Generator = new Generator();
        String[] strings = new String[100];
        for (int i = 0; i < 100; i++) {
            strings[i] = Generator.generateString();
        }
        int counter = 0;
        for (String s : strings) {
            System.out.println(++counter+"- "+s);
        }
    }

    public  String generateString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        String generatedString = buffer.toString();
        return generatedString;
    }
}