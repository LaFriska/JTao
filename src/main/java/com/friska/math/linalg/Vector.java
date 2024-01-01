package com.friska.math.linalg;

public class Vector extends Matrix{

    private final int dimension;

    public Vector(float... inputs) {
        super(toMatrixState(inputs));
        dimension = getRowLength();
    }

    private static float[][] toMatrixState(float[] inputArray){
        float[][] state = new float[inputArray.length][1];
        for (int i = 0; i < inputArray.length; i++) {
            state[i][0] = inputArray[i];
        }
        return state;
    }

    public int getDimension(){
        return dimension;
    }

    public float get(int row) {
        return super.get(row, 0);
    }
}
