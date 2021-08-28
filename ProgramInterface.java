package processor;
import java.util.Scanner;

public class ProgramInterface {

    public ProgramInterface() {}

    public Matrix createMatrix(int counter) {
        Scanner scanner = new Scanner(System.in);
        if (counter == 0) {
            System.out.print("Enter size of matrix: ");
        } else if (counter == 1) {
            System.out.print("Enter size of first matrix: ");
        } else if (counter == 2) {
            System.out.print("Enter size of second matrix: ");
        }
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        Matrix matrix = new Matrix(row, column);
        if (counter == 0) {
            System.out.println("Enter matrix: ");
        } else if (counter == 1) {
            System.out.println("Enter first matrix: ");
        } else if (counter == 2) {
            System.out.println("Enter second matrix: ");
        }
        matrix.fillMatrix(scanner);
        return matrix;
    }


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


    public void transposeChoice() {
        int input = transposeMenu();
        System.out.print("Enter matrix size: ");
        Matrix matrix = createMatrix(0);

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


    public int transposeMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        return Integer.parseInt(scanner.nextLine());
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


    public void inverseChoice() {
        Matrix matrix = createMatrix(0);
        double determinant = matrix.calculateDeterminant(matrix.getMatrix(), matrix.getColumns());
        if (determinant == 0) {
            System.out.println("The matrix doesn't have an inverse.\n");
        } else {
            Matrix cofactorMatrix = matrix.getCofactorMatrix(matrix, matrix.getColumns());
            Matrix transposedCofactorMatrix = cofactorMatrix.mainTranspose(cofactorMatrix);
            Matrix invertedMatrix = transposedCofactorMatrix.multiplyByConstant(1 / determinant);
            invertedMatrix.printMatrix();
            System.out.println();
        }
    }
    public void determinantChoice() {
        Matrix matrix = createMatrix(0);
        double determinant = matrix.calculateDeterminant(matrix.getMatrix(), matrix.getColumns());
        System.out.printf("The result is:\n%s\n\n", determinant);
    }


    public void addChoice() {
        Matrix matrixA = createMatrix(1);
        Matrix matrixB = createMatrix(2);
        Matrix addedMatrix = matrixA.addMatrix(matrixB);
        if (addedMatrix.getPrintError()) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("The result is:");
            addedMatrix.printMatrix();
        }
        System.out.println();
    }

    public void constantMultiChoice() {
        Scanner scanner = new Scanner(System.in);
        Matrix originalMatrix = createMatrix(0);
        System.out.print("Enter constant: ");
        double constant = scanner.nextDouble();
        Matrix multipliedMatrix = originalMatrix.multiplyByConstant(constant);
        System.out.println("The result is:");
        multipliedMatrix.printMatrix();
        System.out.println();
    }

    public void multiplyChoice() {
        Matrix matrixA = createMatrix(1);
        Matrix matrixB = createMatrix(2);
        Matrix multipliedMatrix = matrixA.multiplyByMatrix(matrixB);
        if (multipliedMatrix.getPrintError()) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("The result is:");
            multipliedMatrix.printMatrix();
        }
        System.out.println();
    }
}
