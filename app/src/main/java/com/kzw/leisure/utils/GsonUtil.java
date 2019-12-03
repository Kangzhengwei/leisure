package com.kzw.leisure.utils;

import com.google.gson.Gson;

public class GsonUtil {

    private static volatile Gson gson;

    private GsonUtil() {
    }

    public static Gson getInstance() {
        if (gson == null) {
            synchronized (GsonUtil.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }
}
