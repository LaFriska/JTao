package com.friska.math.questions;

import com.friska.math.linalg.Matrix;
import com.friska.math.linalg.Vector;
import com.friska.math.linalg.tools.MatrixDimension;

import java.awt.*;
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
     * Generates a random matrix with integers.
     * @param dimension Dimension of the matrix, use the #getRandomMatrixDimension method to randomise the dimension.
     * @param lowerbound The lowerbound (inclusive) of randomised integer numbers for entries in the matrix.
     * @param upperbound The upperbound (inclusive) of randomised integer numbers for entries in the matrix.
     * @see #getRandomMatrixDimension
     * **/
    public static Matrix getRandomIntegerMatrix(MatrixDimension dimension, int lowerbound, int upperbound){
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

    public static Vector getRandomIntegerVector(int dimension, int lowerbound, int upperbound){
        return getRandomIntegerMatrix(new MatrixDimension(dimension, 1), lowerbound, upperbound).toVector();
    }

    /**
     * Creates a random matrix dimension with given lower and upper bounds for both the width and height.
     * **/
    public static MatrixDimension getRandomMatrixDimension(int rowLowerBound, int rowUpperBound, int colLowerBound, int colUpperBound){
        rowUpperBound += 1;
        colUpperBound += 1;
        Random rand = new Random();
        return new MatrixDimension(rand.nextInt(rowUpperBound - rowLowerBound) + rowLowerBound, rand.nextInt(colUpperBound - colLowerBound) + colLowerBound);
    }

}
