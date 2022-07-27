package com.ota.tour.util;

import java.util.regex.Pattern;

public class RegexUtils {
    private RegexUtils() {
    }

    public static boolean validate(String patternStr, String source) {
        Pattern pattern = Pattern.compile(patternStr);
        return pattern.matcher(source).matches();
    }
}
