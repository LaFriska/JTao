package com.friska.math.linalg;

public class AugmentedMatrix extends Matrix{
    /**
     * The augmented matrix is a matrix to represent a system of linear equations. This class represents an augmented matrix
     * with an input array, where the constant vector is stored as the last column of the matrix.
     *
     * @param inputArray Input array similar to that of the Matrix class, where it is parsed as an augmented matrix.
     **/
    public AugmentedMatrix(float[][] inputArray) {
        super(inputArray);
    }

    /**
    *
    * */
    public AugmentedMatrix(Matrix coefficients, Vector constants) {
        super(inputArray);
    }
}
