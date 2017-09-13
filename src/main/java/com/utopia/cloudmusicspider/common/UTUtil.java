package com.utopia.cloudmusicspider.common;

public class UTUtil {
    public static String getNumString(String str) {
        String result = "";
        if (str != null) {
            result = str.replaceAll("[^0-9]", "");

        }
        return result;
    }
}
