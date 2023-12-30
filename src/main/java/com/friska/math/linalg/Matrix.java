package linalg;

import exceptions.IncompatibleMatrixException;

public class Matrix {

    private final float[][] state;

    public Matrix(float[][] inputArray){
        if(inputArray == null) throw new IncompatibleMatrixException("The input array must not be null.");
        if(inputArray.length == 0) throw new IncompatibleMatrixException("The input array length must be greater than 0.");
        state = inputArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < state.length; r++){
            sb.append(r == 0 ? "⌈" : r == state.length - 1 ? "⌊" : "|");
            for(int c = 0; c < state.length; c++){

            }
            sb.append(r == 0 ? "⌉" : r == state.length - 1 ? "⌋" : "|");
        }
    }
}
