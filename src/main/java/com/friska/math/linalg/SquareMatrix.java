package com.friska.math.linalg;

import com.friska.math.exceptions.IncompatibleMatrixException;

public class SquareMatrix extends Matrix{

    private final int dimension;
    public SquareMatrix(float[][] inputArray) {
        super(inputArray);
        if(getRowLength() != getColLength()) throw new IncompatibleMatrixException("A square matrix cannot have dimensions of " + getDimensions().toString() + ".");
        dimension = getRowLength();
    }

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

    public int getDimension() {
        return dimension;
    }
}
