package com.friska.math.test;

import com.friska.math.linalg.Matrix;
import com.friska.math.linalg.Vector;

public class Main {
    public static void main(String[] args) {
        testVec();
    }

    private static void testVec(){
        System.out.println(new Vector(new float[]{
                2,3,4,5
        }));
    }

    private static void testMat(){
        System.out.println(new Matrix(new float[][]{
                {2,3,4,5},
                {3,6,42,5.6F},
                {5,1,42,5.62F},
                {3,6,42,5.6F},
        }));

        System.out.println(new Matrix(new float[][]{
                {2},
                {2,3},
                {3,4,5}
        }));
    }
}
