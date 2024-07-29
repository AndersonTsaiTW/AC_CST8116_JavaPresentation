import java.util.Scanner;

public class MatrixOperations {
  public static Scanner scanner = new Scanner(System.in);

  public static double[][] getMatrixFromUser(int rows, int cols) {
    double[][] matrix = new double[rows][cols];
    System.out.println("Please enter a " + rows + "x" + cols + " matrix, using spaces to separate elements:");
    for (int i = 0; i < rows; i++) {
      System.out.print("Input row " + (i + 1) + ": ");
      String[] rowInput = scanner.nextLine().split(" ");
      if (rowInput.length != cols) {
        System.out.println("Error: Row " + (i + 1) + " must have exactly " + cols + " elements.");
        return null;
      }
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = Double.parseDouble(rowInput[j]);
      }
    }
    return matrix;
  }

  public static double[] getVectorFromUser(int size) {
    double[] vector = new double[size];
    System.out.println("Please enter a vector of size " + size + ", using spaces to separate elements:");
    System.out.print("Input vector: ");
    String[] vectorInput = scanner.nextLine().split(" ");
    if (vectorInput.length != size) {
      System.out.println("Error: Vector must have exactly " + size + " elements.");
      return null;
    }
    for (int i = 0; i < size; i++) {
      vector[i] = Double.parseDouble(vectorInput[i]);
    }
    return vector;
  }

  public static double calculateDeterminant(double[][] matrix) {
    return determinant(matrix);
  }

  private static double determinant(double[][] matrix) {
    int n = matrix.length;
    if (n == 1) {
      return matrix[0][0];
    }
    if (n == 2) {
      return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }
    double det = 0;
    for (int i = 0; i < n; i++) {
      det += Math.pow(-1, i) * matrix[0][i] * determinant(getMinor(matrix, 0, i));
    }
    return det;
  }

  private static double[][] getMinor(double[][] matrix, int row, int column) {
    int n = matrix.length;
    double[][] minor = new double[n - 1][n - 1];
    for (int i = 0, mi = 0; i < n; i++) {
      if (i == row)
        continue;
      for (int j = 0, mj = 0; j < n; j++) {
        if (j == column)
          continue;
        minor[mi][mj] = matrix[i][j];
        mj++;
      }
      mi++;
    }
    return minor;
  }

  public static double[][] replaceColumn(double[][] matrix, double[] column, int index) {
    double[][] newMatrix = new double[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        newMatrix[i][j] = matrix[i][j];
      }
      newMatrix[i][index] = column[i];
    }
    return newMatrix;
  }
}
