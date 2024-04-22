package com.example;

public class BitwiseMatrixMultiplication {

    // Define instance variables to store the matrices
    private int [][] matrix1;
    private int [][] matrix2;
<<<<<<< HEAD
    private int [][] result;

    // Getter and setter methods for matrix1
=======
    private int[][] result;
    

>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
    public int[][] getMatrix1() {
        return matrix1;
    }

    public void setMatrix1(int[][] matrix1) {
        this.matrix1 = matrix1;
    }

    // Getter and setter methods for matrix2
    public int[][] getMatrix2() {
        return matrix2;
    }

    public void setMatrix2(int[][] matrix2) {
        this.matrix2 = matrix2;
    }

<<<<<<< HEAD
    // Getter method for the result matrix
=======
>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
    public int[][] getResult() {
        return result;
    }
   
    // Method to perform bitwise matrix multiplication
    public void multiply() {
        // Check if the matrices have compatible dimensions
        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }
<<<<<<< HEAD

        // Initialize the result matrix with appropriate dimensions
=======
>>>>>>> f87b7124d92b7e83c0521b7f6becaf0c49b67d2a
        result = new int[matrix1.length][matrix2[0].length];

        // Perform matrix multiplication using bitwise AND and XOR operations
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                int sum = 0; // Initialize the sum
                for (int k = 0; k < matrix1[0].length; k++) {
                    // Perform bitwise AND and XOR operations
                    sum ^= matrix1[i][k] & matrix2[k][j];
                }
                result[i][j] = sum; // Store the result
            }
        }
    }
}
