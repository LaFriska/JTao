package com.friska.math.linalg;

import com.friska.math.tools.MatrixDimension;
import com.friska.math.tools.NumberUtils;
import com.friska.math.exceptions.IncompatibleMatrixException;

import java.util.Arrays;

public class Matrix {

    private final float[][] state;
    private final MatrixDimension dimensions;


    /**
     * A matrix object is an object where a 2D array of float values are input.
     * **/
    public Matrix(float[][] inputArray){
        if(inputArray == null) throw new IncompatibleMatrixException("The input array must not be null.");
        if(inputArray.length == 0) throw new IncompatibleMatrixException("The input array length must be greater than 0.");
        checkAndFill(inputArray);
        state = inputArray;
        dimensions = new MatrixDimension(state.length, state[0].length);
    }

    /**
     * Checks to make sure that the input array into the matrix is a rectangle. If not, every row length is changed to match
     * the longest row length in the input array, and the newly created slots are filled with 0s.
     **/
    private static void checkAndFill(float[][] inputArray){
        boolean needModification = false;
        int longestRow = inputArray[0].length;
        for(int r = 1; r < inputArray.length; r++){
            if(inputArray[r].length != longestRow){
                needModification = true;
                if(inputArray[r].length > longestRow) longestRow = inputArray[r].length;
            }
        }
        if(needModification) for(int r = 0; r < inputArray.length; r++){
            if(inputArray[r].length != longestRow){
                inputArray[r] = Arrays.copyOf(inputArray[r], longestRow);
            }
        }
    }

    public final MatrixDimension getDimensions() {
        return dimensions;
    }

    public final int getRowLength(){
        return dimensions.row();
    }

    public final int getColLength(){
        return dimensions.col();
    }

    public final boolean isSquare(){
        return dimensions.row() == dimensions.col();
    }

    public final boolean isHorizontalVector(){
        return dimensions.row() == 1;
    }

    public final boolean isVerticalVector(){
        return dimensions.col() == 1;
    }

    public float get(int row, int col){
        if(row >= dimensions.row() || row < 0) throw new IncompatibleMatrixException("Row index of " + row + " is out of bounds.");
        if(col >= dimensions.col() || col < 0) throw new IncompatibleMatrixException("Column index of " + col + " is out of bounds.");
        return state[row][col];
    }

    public SquareMatrix toSquareMatrix(){
        if(dimensions.row() != dimensions.col()) throw new IncompatibleMatrixException("Cannot convert a non-square matrix into a SquareMatrix object.");
        return new SquareMatrix(state);
    }

    public Vector toVector(){
        if(dimensions.col() != 1) throw new IncompatibleMatrixException("Cannot convert a matrix with more than 1 columns to a vector.");
        return getColumnVector(0);
    }

    //--------------------------OPERATIONS------------------------------//

    @Deprecated
    public Matrix multiply(float scalar){
        float[][] newState = state.clone();
        for (int r = 0; r < dimensions.row(); r++) {
            for (int c = 0; c < dimensions.col(); c++) {
                newState[r][c] = scalar * newState[r][c];
            }
        }
        return new Matrix(newState);
    }

    @Deprecated
    public Matrix multiply(int scalar){
        return multiply((float) scalar);
    }


    @Deprecated
    public Vector multiply(Vector vector){
        if(dimensions.col() != vector.getDimension()) throw new IncompatibleMatrixException("The vector's dimension must be equivalent to the matrix's column length.");
        float[][] clonedState = new float[dimensions.row()][dimensions.col()];
        for(int r = 0; r < dimensions.row(); r++){
            for(int c = 0; c < dimensions.col(); c++){
                clonedState[r][c] = state[r][c] * vector.get(c);
            }
        }
        return new Matrix(clonedState).combineColumns();
    }

    @Deprecated
    public Vector combineColumns(){
        float sum;
        float[] vec = new float[dimensions.row()];
        for (int r = 0; r < dimensions.row(); r++) {
            sum = 0;
            for (int c = 0; c < dimensions.col(); c++) {
                sum += state[r][c];
            }
            vec[r] = sum;
        }
        return new Vector(vec);
    }

    public Vector getColumnVector(int columnIndex){
        if(columnIndex >= dimensions.col() || columnIndex < 0) throw new IncompatibleMatrixException("Cannot extract column vector, as column index of " + columnIndex + " is out of bounds.");
        float[] vec = new float[dimensions.row()];
        for(int r = 0; r < dimensions.row(); r++){
            vec[r] = state[r][columnIndex];
        }
        return new Vector(vec);
    }

    public Matrix getRowVector(int rowIndex){
        if(rowIndex >= dimensions.row() || rowIndex < 0) throw new IncompatibleMatrixException("Cannot extract row vector, as row index of " + rowIndex + " is out of bounds.");
        float[][] vec = new float[1][dimensions.col()];
        vec[0] = state[rowIndex];
        return new Matrix(vec);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < dimensions.row(); r++){
            sb.append(dimensions.row() > 1 ? (r == 0 ? "⌈" : r == dimensions.row() - 1 ? "⌊" : "|") : "[");
            for(int c = 0; c < dimensions.col(); c++){
                sb.append(NumberUtils.format(state[r][c]));
                if(c != dimensions.col() - 1) sb.append(" ");
            }
            sb.append(dimensions.row() > 1 ? (r == 0 ? "⌉" : r == dimensions.row() - 1 ? "⌋" : "|") : "]").append("\n");
        }
        return sb.toString();
    }
}
