package com.friska.math.questions;

import com.friska.math.linalg.Linalg;
import com.friska.math.linalg.Matrix;
import com.friska.math.linalg.Vector;

public class Questions {

    public static Question matrixVectorMultiplication(int rowLowerBound, int rowUpperBound, int colLowerBound, int colUpperBound, int lowestPossibleEntry, int highestPossibleEntry){
        Matrix mat = QuestionUtil.getRandomIntegerMatrix(QuestionUtil.getRandomMatrixDimension(rowLowerBound, rowUpperBound, colLowerBound, colUpperBound), lowestPossibleEntry, highestPossibleEntry);
        Vector vec = QuestionUtil.getRandomIntegerVector(mat.getColLength(), lowestPossibleEntry, highestPossibleEntry);
        Matrix res = Linalg.multiply(mat, vec);
        return new Question(mat.getTex() + " " + vec.getTex(), res.getTex());
    }
}
