import java.util.Scanner;

public class MatrixOperationsV1 {
  public static Scanner scanner = new Scanner(System.in);

  public static double[][] getMatrixFromUser(int rows, int cols) {
    double[][] matrix = new double[rows][cols];
    System.out.println("Please enter a " + rows + "x" + cols + " matrix, using spaces to separate elements:");
    for (int i = 0; i < rows; i++) {
      System.out.print("Input row " + (i + 1) + ": ");
      String[] rowInput = scanner.nextLine().split(" ");
      if (rowInput.length != cols) {
        System.out.println("Error: Row " + (i + 1) + " must have exactly " + cols + " elements.");
        return null; // Immediate return on error
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
      double diag = matrix[i][i];
      if (Math.abs(diag) < 1e-10) {
        System.out.println("Matrix is singular or nearly singular");
        return null;
      }
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = matrix[i][j] / diag;
      }

      for (int k = 0; k < rows; k++) {
        if (k != i) {
          double factor = matrix[k][i];
          for (int l = 0; l < cols; l++) {
            matrix[k][l] = matrix[k][l] - factor * matrix[i][l];
          }
        }
      }
      printMatrix(matrix);
    }
    return matrix;
  }

  public static void printMatrix(double[][] matrix) {
    if (matrix == null) {
      System.out.println("No matrix to display.");
      return;
    }
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println(); // Move to the next line after printing each row
    }
    System.out.println();
  }

  public static void main(String[] args) {
    System.out.print("Enter the number of rows and columns (n and n+1): ");
    int n = scanner.nextInt();
    scanner.nextLine(); // Consume the newline left-over

    double[][] A = getMatrixFromUser(n, n + 1);
    if (A == null) {
      return;
    }

    A = gaussJordanElimination(A);
    printMatrix(A);
  }
}
