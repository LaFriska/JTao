package com.friska.math.linalg;

public class Vector extends Matrix{
    public Vector(float[] inputArray) {
        super(toMatrixState(inputArray));
    }

    private static float[][] toMatrixState(float[] inputArray){
        float[][] state = new float[inputArray.length][1];
        for (int i = 0; i < inputArray.length; i++) {
            state[i][0] = inputArray[i];
        }
        return state;
    }
}
