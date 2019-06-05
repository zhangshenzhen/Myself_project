package com.shenzhen.test.util;

import java.util.Arrays;

/**
 * Created by yuanchangzhu on 18-2-2.
 */

public class Utils {
    private Utils() {
    }

    public static String[] mergeStringArray(String[] arrayA, String[] arrayB) {
        String[] projection = Arrays.copyOf(arrayA, arrayA.length + arrayB.length);//数组扩容
        System.arraycopy(arrayB, 0, projection, arrayA.length, arrayB.length);
        return projection;
    }

    public static String sqliteEscape(String keyWord) {
        keyWord = keyWord.replace("/", "//");
        keyWord = keyWord.replace("'", "''");
        keyWord = keyWord.replace("[", "/[");
        keyWord = keyWord.replace("]", "/]");
        keyWord = keyWord.replace("%", "/%");
        keyWord = keyWord.replace("&", "/&");
        keyWord = keyWord.replace("_", "/_");
        keyWord = keyWord.replace("(", "/(");
        keyWord = keyWord.replace(")", "/)");
        return keyWord;
    }

}
