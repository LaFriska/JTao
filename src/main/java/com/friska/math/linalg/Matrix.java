package com.friska.math.linalg;

import com.friska.math.tools.NumberUtils;
import com.friska.math.exceptions.IncompatibleMatrixException;

import java.util.Arrays;

public class Matrix {

    private final float[][] state;



    /**
     * A matrix object is an object where a 2D array of float values are input.
     * **/
    public Matrix(float[][] inputArray){
        if(inputArray == null) throw new IncompatibleMatrixException("The input array must not be null.");
        if(inputArray.length == 0) throw new IncompatibleMatrixException("The input array length must be greater than 0.");
        checkAndFill(inputArray);
        state = inputArray;
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < state.length; r++){
            sb.append(r == 0 ? "⌈" : r == state.length - 1 ? "⌊" : "|");
            for(int c = 0; c < state[0].length; c++){
                sb.append(NumberUtils.format(state[r][c]));
                if(c != state[0].length - 1) sb.append(" ");
            }
            sb.append(r == 0 ? "⌉" : r == state.length - 1 ? "⌋" : "|").append("\n");
        }
        return sb.toString();
    }
}
