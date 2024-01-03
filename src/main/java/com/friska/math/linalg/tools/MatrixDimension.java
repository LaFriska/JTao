package com.friska.math.linalg.tools;


/**
 * Representation of a matrix dimension, with an integer for row, and an integer for column sizes.
 * */
public record MatrixDimension(int row, int col) {
    @Override
    public String toString() {
        return row + " x " + col;
    }

    public static MatrixDimension get(int row, int col){
        return new MatrixDimension(row, col);
    }
}
