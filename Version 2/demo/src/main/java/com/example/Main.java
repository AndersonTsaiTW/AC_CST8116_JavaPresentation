package com.example;

import org.apache.commons.math3.linear.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    // let user input para,aters' matrix size
    public static RealMatrix getSquareMatrixFromUser(int size) {
        double[][] data = new double[size][size];
        System.out
                .println("Please enter a " + size + "x" + size + " square matrix, using spaces to separate elements:");
        for (int i = 0; i < size; i++) {
            System.out.print("Input row " + (i + 1) + ": ");
            String[] rowInput = scanner.nextLine().split("\\s+");
            if (rowInput.length != size) {
                System.out.println("Error: Row " + (i + 1) + " must have exactly " + size + " elements.");
                return null;
            }
            for (int j = 0; j < size; j++) {
                data[i][j] = Double.parseDouble(rowInput[j]);
            }
        }
        return new Array2DRowRealMatrix(data);
    }

    // get the vector(constant) from user
    public static RealVector getVectorFromUser(int size) {
        double[] data = new double[size];
        System.out.println("Please enter a vector of size " + size + ", using spaces to separate elements:");
        String[] inputData = scanner.nextLine().split("\\s+");
        if (inputData.length != size) {
            System.out.println("Error: Vector must have exactly " + size + " elements.");
            return null;
        }
        for (int i = 0; i < size; i++) {
            data[i] = Double.parseDouble(inputData[i]);
        }
        return new ArrayRealVector(data);
    }

    // print matrix
    public static void printMatrix(RealMatrix matrix) {
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                System.out.printf("%.2f ", matrix.getEntry(i, j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter the size of the square matrix: ");
        int size = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        RealMatrix matrix = getSquareMatrixFromUser(size);
        RealVector vector = getVectorFromUser(size);
        if (matrix == null || vector == null) {
            return;
        }

        // 使用 LU 分解求解
        LUDecomposition luDecomposition = new LUDecomposition(matrix);
        double determinant = luDecomposition.getDeterminant();
        System.out.println("Determinant of the matrix: " + determinant);

        if (Math.abs(determinant) < 1e-10) {
            System.out.println("The matrix is singular or nearly singular.");
            return;
        }

        DecompositionSolver solver = luDecomposition.getSolver();
        RealVector solution = solver.solve(vector);

        System.out.println("Solution to the system:");
        for (int i = 0; i < solution.getDimension(); i++) {
            System.out.printf("%.2f ", solution.getEntry(i));
        }
        System.out.println();
    }
}
