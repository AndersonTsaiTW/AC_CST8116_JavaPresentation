public class MatrixSolver {
  public static void main(String[] args) {
    System.out.print("Enter the number of parameters (n): ");
    int n = Integer.parseInt(MatrixOperations.scanner.nextLine());

    double[][] A = MatrixOperations.getMatrixFromUser(n, n);
    if (A == null) {
      return;
    }

    double[] B = MatrixOperations.getVectorFromUser(n);
    if (B == null) {
      return;
    }

    double determinantA = MatrixOperations.calculateDeterminant(A);
    System.out.println("Determinant of matrix A: " + determinantA);

    if (determinantA == 0) {
      System.out.println("Matrix A is singular. No unique solution.");
      return;
    }

    double[] solutions = new double[n];
    for (int i = 0; i < n; i++) {
      double[][] Ai = MatrixOperations.replaceColumn(A, B, i);
      double determinantAi = MatrixOperations.calculateDeterminant(Ai);
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
