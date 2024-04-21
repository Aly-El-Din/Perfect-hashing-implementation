package com.example;

public class BitwiseMatrixMultiplication {

    private int [][] matrix1;
    private int [][] matrix2;
    private int[][] result;
    

    public int[][] getMatrix1() {
        return matrix1;
    }
    public void setMatrix1(int[][] matrix1) {
        this.matrix1 = matrix1;
    }
    public int[][] getMatrix2() {
        return matrix2;
    }
    public void setMatrix2(int[][] matrix2) {
        this.matrix2 = matrix2;
    }

    public int[][] getResult() {
        return result;
    }
   
    public void multiply() {
        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }
        result = new int[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                int sum = 0;// Initialize the sum
                for (int k = 0; k < matrix1[0].length; k++) {
                    // Perform bitwise AND and XOR operations
                    sum ^= matrix1[i][k] & matrix2[k][j];
                }
                result[i][j] = sum; // Store the result
            }
        }
    }
}