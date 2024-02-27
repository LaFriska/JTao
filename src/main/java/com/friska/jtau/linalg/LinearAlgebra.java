package com.friska.jtau.linalg;

import org.jetbrains.annotations.NotNull;

public class LinearAlgebra {

    //--------------Multiplication-----------------//

    /**
     * Multiplies the matrix by a scalar.
     * **/
    public static Matrix multiply(@NotNull Matrix matrix, float scalar){
        float[][] newState = new float[matrix.getRowLength()][matrix.getColLength()];
        for (int r = 0; r < matrix.getRowLength(); r++) {
            for (int c = 0; c < matrix.getColLength(); c++) {
                newState[r][c] = scalar * matrix.get(r,c);
            }
        }
        return new Matrix(newState);
    }

    /**
     * Multiplies the matrix by a scalar.
     * **/
    public static Matrix multiply(@NotNull Matrix matrix, int scalar){
        return multiply(matrix, (float) scalar);
    }
    
    public static Vector multiply(@NotNull Matrix matrix, Vector vector){
        if(matrix.getColLength() != vector.getLength()) throw new IncompatibleMatrixException("The vector's dimension must be equivalent to the matrix's column length.");
        float[][] newState = new float[matrix.getRowLength()][matrix.getColLength()];
        for(int r = 0; r < matrix.getRowLength(); r++){
            for(int c = 0; c < matrix.getColLength(); c++){
                newState[r][c] = matrix.get(r, c) * vector.get(c);
            }
        }
        return combineColumns(new Matrix(newState));
    }

    /**
     * Multiplies the matrix by a matrix.
     * **/
    public static Matrix multiply(@NotNull Matrix matrix, @NotNull Matrix productMatrix){
        if(matrix.getColLength() != productMatrix.getRowLength()) throw new IncompatibleMatrixException("The column length of the matrix must be the same as the row length of the product matrix.");
        Vector[] vectors = new Vector[productMatrix.getColLength()];
        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = multiply(matrix, productMatrix.getColumnVector(i));
        }
        return concatenate(vectors);
    }

    //TODO use dot products instead
    /**
     * Takes the sum of every number in each row, and returns a vector as a result. This
     * is necessary for matrix-vector multiplication.
     * **/
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

    /**
     * Concatenates an array of vectors such that each vector becomes a column vector of
     * an output matrix.
     * **/
    public static Matrix concatenate(@NotNull Vector... vectors){
        if(vectors.length == 0) throw new IncompatibleMatrixException("Cannot concatenate array with 0 vectors.");
        if(vectors.length == 1) return vectors[0];
        float[][] newState = new float[vectors[0].getLength()][vectors.length];
        for (int c = 0; c < vectors.length; c++) {
            if(vectors[c].getLength() != vectors[1].getLength()) throw new IncompatibleMatrixException("Cannot concatenate vectors of varying dimensions.");
            for (int r = 0; r < newState.length; r++) {
                newState[r][c] = vectors[c].get(r);
            }
        }
        return new Matrix(newState);
    }

    /**
     * Concatenates a vector to a matrix such that the vector is appended at the end of the matrix.
     * */
    public static Matrix concatenate(@NotNull Matrix matrix, @NotNull Vector vector){
        if(matrix.getRowLength() != vector.getLength()) throw new IncompatibleMatrixException("Cannot concatenate vector to a matrix of different height.");
        float[][] stateCopy = new float[matrix.getRowLength()][matrix.getColLength() + 1];
        for(int r = 0; r < matrix.getRowLength(); r++){
            for(int c = 0; c < matrix.getColLength(); c++){
                stateCopy[r][c] = matrix.get(r, c);
            }
        }
        for(int r = 0; r < vector.getLength(); r++){
            stateCopy[r][stateCopy[0].length - 1] = vector.get(r);
        }
        return new Matrix(stateCopy);
    }
}
