package com.friska.math.linalg;

import com.friska.math.tools.MatrixDimension;
import com.friska.math.tools.NumberUtils;
import com.friska.math.exceptions.IncompatibleMatrixException;

import java.util.Arrays;

public class Matrix {

    private final float[][] state;
    private final MatrixDimension dimension;


    /**
     * A matrix object is an object where a 2D array of float values are input.
     * **/
    public Matrix(float[][] inputArray){
        if(inputArray == null) throw new IncompatibleMatrixException("The input array must not be null.");
        if(inputArray.length == 0) throw new IncompatibleMatrixException("The input array length must be greater than 0.");
        checkAndFill(inputArray);
        state = inputArray;
        dimension = new MatrixDimension(state.length, state[0].length);
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

    public final MatrixDimension getDimension() {
        return dimension;
    }

    public final int getRowLength(){
        return dimension.row();
    }

    public final int getColLength(){
        return dimension.col();
    }

    public final boolean isSquare(){
        return dimension.row() == dimension.col();
    }

    public final boolean isHorizontalVector(){
        return dimension.row() == 1;
    }

    public final boolean isVerticalVector(){
        return dimension.col() == 1;
    }

    public float get(int row, int col){
        if(row >= dimension.row() || row < 0) throw new IncompatibleMatrixException("Row index of " + row + " is out of bounds.");
        if(col >= dimension.col() || col < 0) throw new IncompatibleMatrixException("Column index of " + col + " is out of bounds.");
        return state[row][col];
    }

    //--------------------------OPERATIONS------------------------------//

    @Deprecated
    public Matrix multiply(float scalar){
        float[][] newState = state.clone();
        for (int r = 0; r < dimension.row(); r++) {
            for (int c = 0; c < dimension.col(); c++) {
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
        if(dimension.col() != vector.getHeight()) throw new IncompatibleMatrixException("The vector's dimension must be equivalent to the matrix's column length.");
        float[][] clonedState = new float[dimension.row()][dimension.col()];
        for(int r = 0; r < dimension.row(); r++){
            for(int c = 0; c < dimension.col(); c++){
                clonedState[r][c] = state[r][c] * vector.getValue(c);
            }
        }
        return new Matrix(clonedState).combineColumns();
    }

    @Deprecated
    public Vector combineColumns(){
        float sum;
        float[] vec = new float[dimension.row()];
        for (int r = 0; r < dimension.row(); r++) {
            sum = 0;
            for (int c = 0; c < dimension.col(); c++) {
                sum += state[r][c];
            }
            vec[r] = sum;
        }
        return new Vector(vec);
    }

    public Vector getColumnVector(int columnIndex){
        if(columnIndex >= dimension.col() || columnIndex < 0) throw new IncompatibleMatrixException("Cannot extract column vector, as column index of " + columnIndex + " is out of bounds.");
        float[] vec = new float[dimension.row()];
        for(int r = 0; r < dimension.row(); r++){
            vec[r] = state[r][columnIndex];
        }
        return new Vector(vec);
    }

    public Matrix getRowVector(int rowIndex){
        if(rowIndex >= dimension.row() || rowIndex < 0) throw new IncompatibleMatrixException("Cannot extract row vector, as row index of " + rowIndex + " is out of bounds.");
        float[][] vec = new float[1][dimension.col()];
        vec[0] = state[rowIndex];
        return new Matrix(vec);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < dimension.row(); r++){
            sb.append(dimension.row() > 1 ? (r == 0 ? "⌈" : r == dimension.row() - 1 ? "⌊" : "|") : "[");
            for(int c = 0; c < dimension.col(); c++){
                sb.append(NumberUtils.format(state[r][c]));
                if(c != dimension.col() - 1) sb.append(" ");
            }
            sb.append(dimension.row() > 1 ? (r == 0 ? "⌉" : r == dimension.row() - 1 ? "⌋" : "|") : "]").append("\n");
        }
        return sb.toString();
    }
}
