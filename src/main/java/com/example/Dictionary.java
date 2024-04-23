package com.example;

public interface Dictionary {
    public String insert(String word);
    public boolean search(String word);
    public String delete(String word);
    public void batchInsert(String filePath);
    public void batchDelete(String filePath);
    public int getNumberofCuurrentElementsinTable();

    public int getCollisions();
}
