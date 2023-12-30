package com.friska.math;

public class NumberUtils {
    public static String format(float value){
        if (value == (int) value) {
            return String.format("%d", (int) value);
        } else {
            return String.format("%s", value);
        }
    }
}
