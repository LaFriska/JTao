package com.friska.math.linalg;

public class Vector extends Matrix{

    private final int height;
    public Vector(float[] inputArray) {
        super(toMatrixState(inputArray));
        height = getRowLength();
    }

    private static float[][] toMatrixState(float[] inputArray){
        float[][] state = new float[inputArray.length][1];
        for (int i = 0; i < inputArray.length; i++) {
            state[i][0] = inputArray[i];
        }
        return state;
    }

    public int getHeight(){
        return height;
    }

    public float getValue(int row) {
        return super.getValue(row, 0);
    }
}
