package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryNSpace implements Dictionary{
    private HashingN<String> hash;

    public DictionaryNSpace(int sizeOfPrimaryTable) {
        this.hash = new HashingN<>(sizeOfPrimaryTable);
    }
    public ArrayList<String> readWordsFromFile(String filePath) {
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
    public boolean validateDeletion(String word){
        return hash.validateDeletion(word);
    }

}