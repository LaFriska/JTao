package com.friska.jtau.linalg;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.friska.jtau.linalg.Matrix.*;
import static com.friska.jtau.linalg.LinearAlgebra.*;


public class LinearAlgebraTest {
    @Test
    public void matrixVectorMult(){
        assertMatrixVectorMult("1,2;2,1", "2;2", "6;6");
    }

    private static void assertMatrixVectorMult(String m, String v, String result){
        assertTrue(compare(multiply(parse(m), Vector.parse(v)), Vector.parse(result)));
    }
}