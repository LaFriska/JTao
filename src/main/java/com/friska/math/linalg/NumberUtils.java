package com.friska.math.linalg;

public class NumberUtils {

    /**
     * Formats string representations of float values so that tailing 0s will be removed.
     * **/
    public static String format(float value){
        if (value == (int) value) {
            return String.format("%d", (int) value);
        } else {
            return String.format("%s", value);
        }
    }
}
