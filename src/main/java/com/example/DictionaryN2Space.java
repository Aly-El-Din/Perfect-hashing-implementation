package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryN2Space implements Dictionary{
    private HashingN2<String> hash;

    public DictionaryN2Space(int sizeOfTable) {
        this.hash = new HashingN2<>(Long.MAX_VALUE,sizeOfTable);
    }
    private ArrayList<String> readWordsFromFile(String filePath) {
        ArrayList<String> wordsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordsList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            // You can handle the exception here, like logging or throwing a custom exception
            throw new RuntimeException("Error reading file");
        }

        return wordsList;
    }

    public void batchInsert(String filePath) {
        ArrayList<String> words = readWordsFromFile(filePath);
        for (String word : words) {
            System.out.println(word+" "+hash.insert(word));
        }
    }
    public void batchDelete(String filePath) {
        ArrayList<String> words = readWordsFromFile(filePath);
        for (String word : words) {
            System.out.println(word+" "+hash.delete(word));
        }
    }

    public String insert(String word) {
        return hash.insert(word);
    }
    public boolean search(String word) {
        return hash.search(word);
    }

    public String delete(String word) {
        return hash.delete(word);
    }
    public void display() {
        hash.display();
    }
}
