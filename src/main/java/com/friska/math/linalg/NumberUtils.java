package com.friska.math.linalg;

public class NumberUtils {

    /**
     * Formats string representations of float values so that tailing 0s will be removed.
     * **/
    public static String format(float value){
        return value == (int) value ?
                                    String.format("%d", (int) value)
                                  : String.format("%s", value);
    }
}
