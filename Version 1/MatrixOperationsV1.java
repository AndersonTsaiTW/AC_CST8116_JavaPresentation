import java.util.Scanner;

public class MatrixOperationsV1 {
  public static Scanner scanner = new Scanner(System.in);

  // Let user input the matrix
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

  public static double[][] gaussJordanElimination(double[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    for (int i = 0; i < rows; i++) {
      // Make every element in the diagonal row zero
      double diag = matrix[i][i];
      if (diag == 0) {
        return null;
      }
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = matrix[i][j] / diag;
      }
      System.out.println("make element in the diagonal row zero for row " + (i + 1));
      printMatrix(matrix);

      // Clear the elements in other rows
      for (int k = 0; k < rows; k++) {
        if (k != i) { // Do not process current row
          double factor = matrix[k][i];
          for (int l = 0; l < cols; l++) {
            matrix[k][l] -= factor * matrix[i][l];
          }
        }
      }
      System.out.println("Clear the elements by row " + (i + 1));
      printMatrix(matrix);
    }
    return matrix;
  }

  public static void printMatrix(double[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println(); // Move to the next line after printing each row
    }
  }

  public static void main(String[] args) {
    System.out.print("Enter the number of parameters (n): ");
    int n = Integer.parseInt(MatrixOperationsV2.scanner.nextLine());

    double[][] A = MatrixOperationsV2.getMatrixFromUser(n, n);
    if (A == null) {
      return;
    }

  }

}
