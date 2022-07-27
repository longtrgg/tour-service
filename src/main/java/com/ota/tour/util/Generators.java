package com.ota.tour.util;

import org.springframework.util.Assert;

import java.util.Locale;

public class Generators {

    public static String buildSequenceName(String collectionName) {
        Assert.hasText(collectionName, "searchCode must be not empty");
        return String.join("_", collectionName.toLowerCase(Locale.ROOT), "sequence");
    }
}
