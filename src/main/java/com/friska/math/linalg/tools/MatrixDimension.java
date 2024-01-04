package com.friska.math.linalg.tools;


import com.friska.math.linalg.exceptions.IncompatibleMatrixException;

/**
 * Representation of a matrix dimension, with an integer for row, and an integer for column sizes.
 * */
public record MatrixDimension(int row, int col) {

    public MatrixDimension{
        if(row <= 0 || col <= 0) throw new IncompatibleMatrixException("Matrix dimensions cannot be " + row + " x " + col);
    }
    @Override
    public String toString() {
        return row + " x " + col;
    }

    public static MatrixDimension get(int row, int col){
        return new MatrixDimension(row, col);
    }
}
