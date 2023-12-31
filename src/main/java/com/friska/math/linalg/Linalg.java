package com.friska.math.linalg;

import com.friska.math.exceptions.IncompatibleMatrixException;
import org.jetbrains.annotations.NotNull;

public class Linalg {

    //--------------Multiplication-----------------//
    public static Matrix multiply(@NotNull Matrix matrix, float scalar){
        float[][] newState = new float[matrix.getRowLength()][matrix.getColLength()];
        for (int r = 0; r < matrix.getRowLength(); r++) {
            for (int c = 0; c < matrix.getColLength(); c++) {
                newState[r][c] = scalar * matrix.get(r,c);
            }
        }
        return new Matrix(newState);
    }
    
    public static Matrix multiply(@NotNull Matrix matrix, int scalar){
        return multiply(matrix, (float) scalar);
    }
    
    public static Vector multiply(@NotNull Matrix matrix, Vector vector){
        if(matrix.getColLength() != vector.getHeight()) throw new IncompatibleMatrixException("The vector's dimension must be equivalent to the matrix's column length.");
        float[][] newState = new float[matrix.getRowLength()][matrix.getColLength()];
        for(int r = 0; r < matrix.getRowLength(); r++){
            for(int c = 0; c < matrix.getColLength(); c++){
                newState[r][c] = matrix.get(r, c) * vector.getValue(c);
            }
        }
        return combineColumns(new Matrix(newState));
    }

    public static Matrix multiply(@NotNull Matrix matrix, @NotNull Matrix productMatrix){
        if(matrix.getColLength() != productMatrix.getRowLength()) throw new IncompatibleMatrixException("The column length of the matrix must be the same as the row length of the product matrix.");
        Vector[] vectors = new Vector[productMatrix.getColLength()];
        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = multiply(matrix, productMatrix.getColumnVector(i));
        }
        return concatenateVectors(vectors);
    }

    public static Vector combineColumns(@NotNull Matrix mat){
        float sum;
        float[] vec = new float[mat.getRowLength()];
        for (int r = 0; r < mat.getRowLength(); r++) {
            sum = 0;
            for (int c = 0; c < mat.getColLength(); c++) {
                sum += mat.get(r,c);
            }
            vec[r] = sum;
        }
        return new Vector(vec);
    }

    public static Matrix concatenateVectors(@NotNull Vector... vectors){
        if(vectors.length == 0) throw new IncompatibleMatrixException("Cannot concatenate vectors array of 0 length.");
        float[][] newState = new float[vectors[0].getHeight()][vectors.length];
        for (int c = 0; c < vectors.length; c++) {
            if(vectors[c].getHeight() != vectors[1].getHeight()) throw new IncompatibleMatrixException("Cannot concatenate vectors of varying dimensions.");
            for (int r = 0; r < newState.length; r++) {
                newState[r][c] = vectors[c].getValue(r);
            }
        }
        return new Matrix(newState);
    }
}
