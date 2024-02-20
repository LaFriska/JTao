package com.friska.math.questions;

import com.friska.math.linalg.LinearAlgebra;
import com.friska.math.linalg.Matrix;
import com.friska.math.linalg.Vector;

public class Questions {

    /**
     * Creates a random matrix-vector multiplication question with the following parameters for the RNG.
     *
     * @param lowestR Lowest possible row length
     * @param largestR Largest possible row length
     * @param lowestC Lowest possible column length
     * @param largestC Largest possible column length
     * @param lowlim Lowest possible matrix/vector integer entry
     * @param upplim Lowest possible matrix/vector integer entry
     * **/
    public static Question matrixVectorMultiplication(int lowestR, int largestR, int lowestC, int largestC, int lowlim, int upplim){
        Matrix mat = QuestionUtil.getRandomIntegerMatrix(QuestionUtil.getRandomMatrixDimension(lowestR, largestR, lowestC, largestC), lowlim, upplim);
        Vector vec = QuestionUtil.getRandomIntegerVector(mat.getColLength(), lowlim, upplim);
        Matrix res = LinearAlgebra.multiply(mat, vec);
        return new Question(mat.getTex() + " " + vec.getTex(), res.getTex());
    }
}
