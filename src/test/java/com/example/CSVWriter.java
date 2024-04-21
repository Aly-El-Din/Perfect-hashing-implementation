package com.example;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public void writeData(String fileName, int inputSize, int sampleSize, long[] averageTimesPerInsertion,
            int[] spaceComplexities) {
        try {
            FileWriter writer = new FileWriter(fileName);
            
            writer.append("Average Time Per Insertion");
            writer.append(",");
            writer.append("Space Complexity");
            writer.append("\n");

            for (int i = 0; i < sampleSize; i++) {
              
                writer.append(String.valueOf(averageTimesPerInsertion[i]));
                writer.append(",");
                writer.append(String.valueOf(spaceComplexities[i]));
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

}
