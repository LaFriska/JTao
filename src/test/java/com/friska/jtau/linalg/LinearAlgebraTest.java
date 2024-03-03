package com.friska.jtau.linalg;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.friska.jtau.linalg.Matrix.*;
import static com.friska.jtau.linalg.LinearAlgebra.*;


public class LinearAlgebraTest {
    @Test
    public void matrixVectorMult(){

        assertTrueMV("1,2;2,1", "2;2", "6;6");
        assertTrueMV("2,0,-5;-1,3,1", "2;2;1", "-1;5");
        assertTrueMV("1,4", "3;4", "19");
        assertTrueMV("12.5, 4.1, 3, 5, 12; 7,8,9,0,0; 9.87, 4,4,4,4;0,3,2,1,9;7.5,6.5,5.5,4.4,1", "3;4;5;9;8", "209.9;98;133.61;103;123.600006");
        assertTrueMV("1,2,3,4;5,6,7,8;9,2,4,6;7,0,7,7;9,9,9,9;11,12,13,14;9,7,5,3", "135; 549; 230; 789", "5079;11891;7967;8078;15327;22109;8575");
        assertTrueMV("8972, 7777, 99000; 409, 509, 609; 6543, 3456, 6789", "90; 48; 27", "3853776;77685;938061");

        assertFalseMV("1,3,9", "1;1;1", "1");
        assertFalseMV("1,2,3,4;5,6,7,8;9,1,4,6;7,0,7,7;9,9,9,9;11,12,13,14;9,7,5,3", "135; 549; 230; 789", "5079;11891;7967;8078;15327;22109;8575");
    }

    private static void assertTrueMV(String m, String v, String result){
        assertTrue(compare(multiply(parse(m), Vector.parse(v)), Vector.parse(result)));
    }
    private static void assertFalseMV(String m, String v, String result){
        assertFalse(compare(multiply(parse(m), Vector.parse(v)), Vector.parse(result)));
    }
}