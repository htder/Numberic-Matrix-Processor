package processor;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.round;

public class Matrix {
    int rows;
    int columns;
    double[][] matrix;
    boolean printError;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
        this.printError = false;
    }

    public int getRows() {
        return this.rows;
    }
    public int getColumns() {
        return this.columns;
    }
    public double[][] getMatrix() {
        return this.matrix;
    }
    public boolean getPrintError() { return this.printError; }
    public void setPrintError(boolean value) {
        this.printError = value;
    }

    public double calculateDeterminant(double[][] matrix, int n) {
        double determinant = 0;
        int sign = 1;
        if (n == 1) {
            return matrix[0][0];
        }
        for (int i = 0; i < n; i++) {
            determinant += sign * matrix[0][i] * calculateDeterminant(getReducedMatrix(matrix,0, i, n), n - 1);
            sign *= -1;
        }
        return determinant;
    }

    public Matrix getCofactorMatrix(Matrix matrix, int n) {
        Matrix returnMatrix = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j <  matrix.getColumns(); j++) {
                returnMatrix.getMatrix()[i][j] = Math.pow(-1, i + j + 2) * calculateDeterminant(getReducedMatrix(matrix.getMatrix(), i, j, n), n - 1);
            }
        }
        return returnMatrix;
    }

    public double[][] getReducedMatrix(double[][] matrix, int p, int q, int n) {
        double[][] reducedMatrix = new double[n][n];
        int row = 0;
        int column = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != p && j != q) {
                    reducedMatrix[row][column++] = matrix[i][j];
                    if (column == n - 1) {
                        column = 0;
                        row++;
                    }
                }
            }
        }
        return reducedMatrix;
    }

    public Matrix horizontalTranspose(Matrix matrix) {
        Matrix returnMatrix = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                returnMatrix.getMatrix()[i][j] = matrix.getMatrix()[matrix.getRows() - 1 - i][j];
            }
        }
        return returnMatrix;
    }

    public Matrix verticalTranspose(Matrix matrix) {
        Matrix returnMatrix = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                returnMatrix.getMatrix()[i][j] = matrix.getMatrix()[i][matrix.getColumns() - 1 - j];
            }
        }
        return returnMatrix;
    }

    public Matrix sideTranspose(Matrix matrix) {
        Matrix returnMatrix = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                returnMatrix.getMatrix()[i][j] = matrix.getMatrix()[matrix.getColumns() - 1 - j][matrix.getRows() - 1 - i];
            }
        }
        return returnMatrix;
    }

    public Matrix mainTranspose(Matrix matrix) {
        Matrix returnMatrix = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                returnMatrix.getMatrix()[i][j] = matrix.getMatrix()[j][i];
            }
        }
        return returnMatrix;
    }

    public Matrix multiplyByMatrix(Matrix matrixB) {
        Matrix returnMatrix = new Matrix(this.rows, matrixB.getColumns());
        if (this.columns != matrixB.getRows()) {
            returnMatrix.setPrintError(true);
            return returnMatrix;
        } else {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < matrixB.getColumns(); j++) {
                    double sum = 0;
                    for (int k = 0; k < this.columns; k++) {
                        sum += this.matrix[i][k] * matrixB.getMatrix()[k][j];
                    }
                    returnMatrix.getMatrix()[i][j] = sum;
                }
            }
            return returnMatrix;
        }
    }

    public Matrix multiplyByConstant(double constant) {
        Matrix returnMatrix = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                returnMatrix.getMatrix()[i][j] = this.matrix[i][j] * constant;
            }
        }
        return returnMatrix;
    }

    public Matrix addMatrix(Matrix matrixB) {
        Matrix returnMatrix = new Matrix(matrixB.getRows(), matrixB.getColumns());
        if (this.rows != matrixB.getRows() || this.columns != matrixB.getColumns()) {
            returnMatrix.setPrintError(true);
        } else {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    returnMatrix.getMatrix()[i][j] = this.matrix[i][j] + matrixB.getMatrix()[i][j];
                }
            }
        }
        return returnMatrix;
    }

    public void fillMatrix(Scanner scanner) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    public void printMatrix() {
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.printf("%s ", df.format(this.matrix[i][j]));
            }
            System.out.println();
        }
    }

}
