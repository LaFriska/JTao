package com.friska.math.linalg;

import com.friska.math.exceptions.IncompatibleMatrixException;

public class Linalg {

    //--------------Multiplication-----------------//
    public static Matrix multiply(Matrix matrix, float scalar){
        float[][] newState = new float[matrix.getRowLength()][matrix.getColLength()];
        for (int r = 0; r < matrix.getRowLength(); r++) {
            for (int c = 0; c < matrix.getColLength(); c++) {
                newState[r][c] = scalar * matrix.get(r,c);
            }
        }
        return new Matrix(newState);
    }
    
    public static Matrix multiply(Matrix matrix, int scalar){
        return multiply(matrix, (float) scalar);
    }
    
    public static Vector multiply(Matrix matrix, Vector vector){
        if(matrix.getColLength() != vector.getHeight()) throw new IncompatibleMatrixException("The vector's dimension must be equivalent to the matrix's column length.");
        float[][] newState = new float[matrix.getRowLength()][matrix.getColLength()];
        for(int r = 0; r < matrix.getRowLength(); r++){
            for(int c = 0; c < matrix.getColLength(); c++){
                newState[r][c] = matrix.get(r, c) * vector.getValue(c);
            }
        }
        return combineColumns(new Matrix(newState));
    }

    public static Vector combineColumns(Matrix mat){
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
}
