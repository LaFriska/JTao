package com.friska.jtau.questions;

import com.friska.jtau.linalg.Matrix;
import com.friska.jtau.linalg.Vector;
import com.friska.jtau.linalg.IncompatibleMatrixException;
import com.friska.jtau.linalg.MatrixDimension;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Static methods for the generation of randomised objects and numbers used in
 * questions. Note that every bound is inclusive, for the ease of use. For example,
 * a lower bound of -12 and an upper bound of 12 would mean that the random integer R
 * is -12 =< R =< 12, as -12 and 12, and every integer in between are both possible
 * solutions. This would mean that in most cases, the upper bounds input will be transformed
 * with an addition of 1 to fit the exclusivity of upper bounds in Java's Random class.
 * **/
public class QuestionUtil {

    /**
     * Generates a random matrix with integer entries.
     * @param dimension Dimension of the matrix, use the #getRandomMatrixDimension method to randomise the dimension.
     * @param lowerbound The lowerbound (inclusive) of randomised integer numbers for entries in the matrix.
     * @param upperbound The upperbound (inclusive) of randomised integer numbers for entries in the matrix.
     * @see #getRandomMatrixDimension
     * **/
    public static @NotNull Matrix getRandomIntegerMatrix(@NotNull MatrixDimension dimension, int lowerbound, int upperbound){
        upperbound += 1;
        Random rand = new Random();
        float[][] state = new float[dimension.row()][dimension.col()];
        for (int r = 0; r < dimension.row(); r++) {
            for (int c = 0; c < dimension.col(); c++) {
                state[r][c] = rand.nextInt(upperbound - lowerbound) + lowerbound;
            }
        }
        return new Matrix(state);
    }

    /**
     * Generates a random vector with integer entries.
     * @param dimension The height of the vector. Since the vector's column length is always 1, only an integer is needed
     *                  to represent its dimension.
     * @param lowerbound The lowerbound (inclusive) of randomised integer numbers for entries in the vector.
     * @param upperbound The upperbound (inclusive) of randomised integer numbers for entries in the vector.
     * **/
    public static @NotNull Vector getRandomIntegerVector(int dimension, int lowerbound, int upperbound){
        if(dimension <= 0) throw new IncompatibleMatrixException("Cannot process a vector with a dimension less than 1");
        return getRandomIntegerMatrix(new MatrixDimension(dimension, 1), lowerbound, upperbound).toVector();
    }

    /**
     * Creates a random matrix dimension with given lower and upper bounds for both the width and height.
     * **/
    public static @NotNull MatrixDimension getRandomMatrixDimension(int rowLowerBound, int rowUpperBound, int colLowerBound, int colUpperBound){
        rowUpperBound += 1;
        colUpperBound += 1;
        Random rand = new Random();
        return new MatrixDimension(rand.nextInt(rowUpperBound - rowLowerBound) + rowLowerBound, rand.nextInt(colUpperBound - colLowerBound) + colLowerBound);
    }

}
