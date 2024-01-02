package com.friska.math.linalg;

import com.friska.math.exceptions.IncompatibleMatrixException;

public class SquareMatrix extends Matrix{

    private final int dimension;

    private Float determinant = null;

    /**
     * A square matrix is a matrix with the same number of rows as its number
     * of columns. This distinction is necessary for computing determinants, and
     * cofactor expansions. A matrix objects can become a square matrix, or vise versa.
     * **/
    public SquareMatrix(float[][] inputArray) {
        super(inputArray);
        if(getRowLength() != getColLength()) throw new IncompatibleMatrixException("A square matrix cannot have dimensions of " + getDimensions().toString() + ".");
        dimension = getRowLength();
    }

    /**
     * Calculates and returns the determinant of the matrix. This method uses cofactor expansion; and thus, is computationally very
     * expensive, as it recursively finds the determinants of minor matrices. Thus, this method will likely not work for square matrices
     * of very high dimensions. As a way to save computing power, once this method successfully calculates the determinant, it will
     * be stored as a global variable, which would be retrieved again next time this method is called, meaning that for every instance
     * of SquareMatrix, the recursive algorithm will only need to be called once to get the determinant.
     * **/
    public float getDeterminant(){
        if(determinant != null) return determinant;
        float sum = 0;
        if(dimension == 1) return get(0,0);
        for (int i = 0; i < dimension; i++) {
            sum += (float) (get(0, i) * Math.pow(-1, i)) * getMinorMatrix(0, i).getDeterminant();
        }
        determinant = sum;
        return sum;
    }

    /**
     * With given coordinates, that starts from 0 (meaning that the first entry into the matrix is always 0), returns
     * the minor matrix where a square matrix if formed after eliminating every value with the given row coordinates, and
     * eliminating every value with the given column coordinates.
     * **/
    public SquareMatrix getMinorMatrix(int rowCoords, int colCoords){
        if(rowCoords >= dimension || colCoords >= dimension) throw new IndexOutOfBoundsException("Cannot process " + rowCoords + " and " + colCoords + " as coordinates for minor matrices.");
        if(dimension == 1) return null;
        int rowPointer = 0;
        int colPointer;
        float[][] newState = new float[dimension - 1][dimension - 1];
        for (int r = 0; r < dimension; r++) {
            colPointer = 0;
            if(r != rowCoords) {
                for (int c = 0; c < dimension; c++) {
                    if(c != colCoords){
                        newState[rowPointer][colPointer] = get(r, c);
                        colPointer++;
                    }
                }
                rowPointer++;
            }
        }
        return new SquareMatrix(newState);
    }

    /**
     * Returns the dimension of the square matrix. In the case of a square matrix, a dimension could
     * be represented by just an integer, instead of two values.
     * **/
    public int getDimension() {
        return dimension;
    }
}
