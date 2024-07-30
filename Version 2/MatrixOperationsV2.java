import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;

import java.util.Scanner;

public class MatrixOperationsV2 {
  private static Scanner scanner = new Scanner(System.in);

  // get the metrix from user
  public static RealMatrix getMatrixFromUser(int rows, int cols) {
    double[][] data = new double[rows][cols];
    System.out.println("Please enter a " + rows + "x" + cols + " matrix, using spaces to separate elements:");
    for (int i = 0; i < rows; i++) {
      System.out.print("Input row " + (i + 1) + ": ");
      String[] rowInput = scanner.nextLine().split("\\s+");
      if (rowInput.length != cols) {
        System.out.println("Error: Row " + (i + 1) + " must have exactly " + cols + " elements.");
        return null;
      }
      for (int j = 0; j < cols; j++) {
        data[i][j] = Double.parseDouble(rowInput[j]);
      }
    }
    return new Array2DRowRealMatrix(data);
  }

  // print the metrix
  public static void printMatrix(RealMatrix matrix) {
    for (int i = 0; i < matrix.getRowDimension(); i++) {
      for (int j = 0; j < matrix.getColumnDimension(); j++) {
        System.out.printf("%.2f ", matrix.getEntry(i, j));
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    System.out.print("Enter the number of rows and columns (n and n+1 for augmented matrix): ");
    int n = scanner.nextInt();
    scanner.nextLine(); // 消费换行符

    RealMatrix A = getMatrixFromUser(n, n + 1);
    if (A == null) {
      System.out.println("Invalid matrix input.");
      return;
    }

    // 使用 LU 分解求解
    DecompositionSolver solver = new LUDecomposition(A).getSolver();
    RealMatrix identity = MatrixUtils.createRealIdentityMatrix(n);
    RealMatrix solution = solver.solve(identity);

    System.out.println("The matrix after Gauss-Jordan elimination is:");
    printMatrix(solution);
  }
}
