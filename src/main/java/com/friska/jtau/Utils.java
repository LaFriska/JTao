package com.friska.jtau;

import org.jetbrains.annotations.NotNull;

public class Utils {

    /**
     * Formats string representations of float values so that tailing 0s will be removed.
     * **/
    public static String format(float value){
        return value == (int) value ?
                                    String.format("%d", (int) value)
                                  : String.format("%s", value);
    }

    /**
     * Removes all white space
     * */
    public static String removeWhitespace(@NotNull String input) {
        return input.replaceAll("\\s", "");
    }
}
