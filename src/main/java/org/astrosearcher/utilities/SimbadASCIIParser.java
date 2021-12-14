package org.astrosearcher.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used for parsing the Simbad ASCII response that is used for obtaining
 * the measurements for given astronomical object.
 *
 * @author Ľuboslav Halama
 */
public class SimbadASCIIParser {

    @AllArgsConstructor
    @Getter
    public class Category {
        String name;
        int    count;
    }

    public List<SimbadMeasurementsTable> parseForMeasurements(String response) {

        List<SimbadMeasurementsTable> measurements          = new ArrayList<>();
        List<Category>                measurementCategories = new ArrayList<>();
        List<String>                  valuesFromCategory    = new ArrayList<>();

        // Skip anything before measurements
        Scanner scaner = new Scanner(response);
        String line = scaner.hasNextLine() ? scaner.nextLine() : null;
        while ( line != null && !line.startsWith("Measures")) {
            line = scaner.nextLine();
        }

        // parse categories
        Pattern pattern = Pattern.compile(RegularExpressions.SIMBAD_ASCII_MEASURE_CAT);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            measurementCategories.add(new Category(matcher.group(1), Integer.parseInt(matcher.group(2))));
        }

        // parse measurements rows
        for (Category measurementCategory : measurementCategories) {
            for (int measureIndex = 0; measureIndex < measurementCategory.getCount() + 1; measureIndex++) {
                line = scaner.nextLine();
                valuesFromCategory.add(line);
            }
            measurements.add(new SimbadMeasurementsTable(measurementCategory, valuesFromCategory));
            valuesFromCategory.clear();
        }

        return measurements;
    }


}
