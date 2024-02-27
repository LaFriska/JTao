package com.friska.jtau.linalg;

public class Vector extends Matrix{

    private final int dimension;

    /**
     * A vector is a matrix with only 1 column. In this case, a single 1-dimensional float array is used,
     * and unlike a matrix, it does <b>not</b> represent a row, but rather, the single column vector in the
     * vector.
     * **/
    public Vector(float... inputs) {
        super(toMatrixState(inputs));
        dimension = getRowLength();
    }

    /**
     * Returns a 2D float array representation of the vector, where each value is represented by
     * a row.
     * **/
    private static float[][] toMatrixState(float[] inputArray){
        float[][] state = new float[inputArray.length][1];
        for (int i = 0; i < inputArray.length; i++) {
            state[i][0] = inputArray[i];
        }
        return state;
    }

    /**
     * Returns the dimension of the vector. In the case of a vector, a dimension could
     * be represented by just an integer, instead of two values.
     * **/
    public int getLength(){
        return dimension;
    }

    /**
     * Returns the dimension of the vector. In the case of a vector, a dimension could
     * be represented by just an integer, instead of two values.
     * **/
    public int getDimension(){return dimension;}

    /**
     * Returns a value in the vector with a given row index.
     * **/
    public float get(int row) {
        return super.get(row, 0);
    }
}
