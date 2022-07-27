package com.ota.tour.util;

import com.ota.tour.data.model.Translation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

    public static void updateTranslation(List<Translation> source, List<Translation> desc) {
        if (source == null || desc == null) {
            return;
        }
        source.forEach(itemSource -> desc.removeIf(itemDesc -> itemSource.getLanguage() == itemDesc.getLanguage()));
        desc.addAll(source);
    }

    public static void updateTranslation(Translation source, List<Translation> desc) {
        if (source == null || desc == null) {
            return;
        }
        desc.removeIf(itemDesc -> source.getLanguage() == itemDesc.getLanguage());
        desc.add(source);
    }

    public static List<Translation> buildTranslations(Translation... translation) {
        return new ArrayList<>(Arrays.asList(translation));
    }

}
