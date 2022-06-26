package com.swp.SchoolManagement.util;

import lombok.Data;

public class Util {
    public static String parseString(Object obj) {
        if (obj == null)
            return "";
        return String.valueOf(obj);
    }

    public static Long parseLong(Object obj) {
        if (obj == null)
            return 0L;
        return Long.parseLong(obj.toString());
    }

    public static Double parseDouble(Object obj) {
        if (obj == null)
            return 0.0;
        return Double.parseDouble(obj.toString());
    }

    public static Integer parseInt(Object obj) {
        if (obj == null)
            return 0;
        return Integer.parseInt(obj.toString());
    }
    public static Boolean parseBoolean(Object obj) {
        Boolean result = false;
        String value = String.valueOf(obj);
        if (value.equalsIgnoreCase("true") || value.equals("1")) {
            result = true;
        }
        return result;
    }
}
