package processor;
import java.util.Scanner;

public class ProgramInterface {

    public ProgramInterface() {}

    public void start() {
        while (true) {
            int input = Menu();
            if (input == 0) {
                break;
            }
            switch (input) {
                case 1:
                    addChoice();
                    break;
                case 2:
                    constantMultiChoice();
                    break;
                case 3:
                    multiplyChoice();
                    break;
                case 4:
                    transposeChoice();
                    break;
                case 5:
                    determinantChoice();
                    break;
                case 6:
                    inverseChoice();
                    break;
            }
        }
    }

    public void inverseChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter matrix size: ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        Matrix matrix = new Matrix(row, column);
        System.out.println("Enter matrix:");
        matrix.fillMatrix(scanner);
        double result = matrix.calculateDeterminant(matrix.getMatrix(), matrix.getColumns());
        if (result == 0) {
            System.out.println("The matrix doesn't have an inverse.");
        } else {
            Matrix newMatrix = matrix.getCofactorMatrix(matrix, matrix.getColumns());
            Matrix m1 = newMatrix.mainTranspose(newMatrix);
            Matrix m2 = m1.multiplyByConstant(1 / result);
            m2.printMatrix();
            System.out.println();
        }
    }
    public void determinantChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter matrix size: ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        Matrix matrix = new Matrix(row, column);
        System.out.println("Enter matrix:");
        matrix.fillMatrix(scanner);
        double result = matrix.calculateDeterminant(matrix.getMatrix(), matrix.getColumns());
        System.out.printf("The result is:\n%s\n\n", result);
    }

    public void transposeChoice() {
        Scanner scanner = new Scanner(System.in);
        int input = transposeMenu();
        System.out.print("Enter matrix size: ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        Matrix matrix = new Matrix(row, column);
        System.out.println("Enter matrix:");
        matrix.fillMatrix(scanner);
        switch (input) {
            case 1:
                mainDiagonal(matrix);
                break;
            case 2:
                sideDiagonal(matrix);
                break;
            case 3:
                verticalDiagonal(matrix);
                break;
            case 4:
                horizontalDiagonal(matrix);
                break;
        }
    }

    public void mainDiagonal(Matrix matrix) {
        Matrix transposedMatrix = matrix.mainTranspose(matrix);
        transposedMatrix.printMatrix();
    }

    public void sideDiagonal(Matrix matrix) {
        Matrix transposedMatrix = matrix.sideTranspose(matrix);
        transposedMatrix.printMatrix();
    }

    public void verticalDiagonal(Matrix matrix) {
        Matrix transposedMatrix = matrix.verticalTranspose(matrix);
        transposedMatrix.printMatrix();
    }

    public void horizontalDiagonal(Matrix matrix) {
        Matrix transposedMatrix = matrix.horizontalTranspose(matrix);
        transposedMatrix.printMatrix();
    }

    public void addChoice() {
        Matrix matrixA = createFirst();
        Matrix matrixB = createSecond();
        Matrix matrixC = matrixA.addMatrix(matrixB);
        if (matrixC.getPrintError()) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("The result is:");
            matrixC.printMatrix();
        }
        System.out.println();
    }

    public void constantMultiChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of matrix: ");
        int rowA = scanner.nextInt();
        int columnA = scanner.nextInt();
        Matrix matrixA = new Matrix(rowA, columnA);
        System.out.println("Enter matrix:");
        matrixA.fillMatrix(scanner);
        System.out.print("Enter constant: ");
        double constant = scanner.nextDouble();
        Matrix matrixB = matrixA.multiplyByConstant(constant);
        System.out.println("The result is:");
        matrixB.printMatrix();
        System.out.println();
    }

    public void multiplyChoice() {
        Matrix matrixA = createFirst();
        Matrix matrixB = createSecond();
        Matrix matrixC = matrixA.multiplyByMatrix(matrixB);
        if (matrixC.getPrintError()) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("The result is:");
            matrixC.printMatrix();
        }
        System.out.println();
    }


    public int Menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your Choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int transposeMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Matrix createFirst() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of first matrix: ");
        int rowA = scanner.nextInt();
        int columnA = scanner.nextInt();
        Matrix matrixA = new Matrix(rowA, columnA);
        System.out.println("Enter first matrix: ");
        matrixA.fillMatrix(scanner);
        return matrixA;
    }

    public Matrix createSecond() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of second matrix: ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        Matrix matrix = new Matrix(row, column);
        System.out.println("Enter second matrix: ");
        matrix.fillMatrix(scanner);
        return matrix;
    }
}
