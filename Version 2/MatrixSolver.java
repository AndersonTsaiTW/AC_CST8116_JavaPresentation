public class MatrixSolver {
  public static void main(String[] args) {
    System.out.print("Enter the number of parameters (n): ");
    int n = Integer.parseInt(MatrixOperationsV2.scanner.nextLine());

    double[][] A = MatrixOperationsV2.getMatrixFromUser(n, n);
    if (A == null) {
      return;
    }

    double[] B = MatrixOperationsV2.getVectorFromUser(n);
    if (B == null) {
      return;
    }

    double determinantA = MatrixOperationsV2.calculateDeterminant(A);
    System.out.println("Determinant of matrix A: " + determinantA);

    if (determinantA == 0) {
      System.out.println("Matrix A is singular. No unique solution.");
      return;
    }

    double[] solutions = new double[n];
    for (int i = 0; i < n; i++) {
      double[][] Ai = MatrixOperationsV2.replaceColumn(A, B, i);
      double determinantAi = MatrixOperationsV2.calculateDeterminant(Ai);
      solutions[i] = determinantAi / determinantA;
      System.out.println("Determinant of matrix A with column " + (i + 1) + " replaced: " + determinantAi);
    }

    System.out.print("Variables: ");
    for (double solution : solutions) {
      System.out.print(solution + " ");
    }
    System.out.println();
  }
}
