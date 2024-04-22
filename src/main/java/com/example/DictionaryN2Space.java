package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryN2Space implements Dictionary{
    private HashingN2<String> hash;

    public DictionaryN2Space(int sizeOfTable) {
        this.hash = new HashingN2<>(true,Long.MAX_VALUE,sizeOfTable);
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
        hash.rehash(words.size()+hash.getExpectedNumberOfElements());
        int totalInsertedElements = 0;
        int totalDuplicateElements = 0;
        for (String word : words) {
            String message = hash.insert(word);
            if(message.equals("Inserted successfully")){
                totalInsertedElements++;
            }
            else if(message.equals("Already exists in the table")){
                totalDuplicateElements++;
            }
        }
        System.out.println("\u001B[32m\nTotal Inserted Elements: " + totalInsertedElements + "\u001B[0m");
        System.out.println("\u001B[31mTotal Duplicate Elements: " + totalDuplicateElements + "\u001B[0m");
    }
    public void batchDelete(String filePath) {
        ArrayList<String> words = readWordsFromFile(filePath);
        int totalDeletedElements = 0;
        int totalNotFoundElements = 0;
        for (String word : words) {
            String message = hash.delete(word);
            if(message.equals("Deleted successfully")){
                totalDeletedElements++;
            }
            else if(message.equals("Element not found")){
                totalNotFoundElements++;
            }
        }
        System.out.println("\u001B[32m\nTotal Deleted Elements: " + totalDeletedElements + "\u001B[0m");
        System.out.println("\u001B[31mTotal Not Found Elements: " + totalNotFoundElements + "\u001B[0m");
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
    
    public int getCount(){
        return hash.getCount();
    }
}
