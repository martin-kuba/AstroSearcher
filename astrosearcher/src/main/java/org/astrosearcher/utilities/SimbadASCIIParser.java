package org.astrosearcher.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimbadASCIIParser {

    @AllArgsConstructor
    @Getter
    public class Category {
        String name;
        int    count;
    }

    public List<SimbadMeasurementsTable> parseForMeasurements(String response) {
        if (Limits.DEBUG) {
            System.out.print("        Response:\n" + response + "\n\n");
        }

        List<SimbadMeasurementsTable> measurements = new ArrayList<>();
        List<Category>                measurementCategories = new ArrayList<>();
        List<String>                  valuesFromCategory = new ArrayList<>();

        // Skip anything before measurements
        Scanner scaner = new Scanner(response);
        String line = scaner.hasNextLine() ? scaner.nextLine() : null;
        while ( line != null && !line.startsWith("Measures")) {
            line = scaner.nextLine();
        }


        // parse categories
        Pattern pattern = Pattern.compile(RegularExpressions.SIMBAD_ASCII_MEASURE_CAT);
        Matcher matcher = pattern.matcher(line);

        if (Limits.DEBUG) {
            System.out.print("        Searching for measurements catagories...");
        }
        while (matcher.find()) {
            measurementCategories.add(new Category(matcher.group(1), Integer.parseInt(matcher.group(2))));
        }
        if (Limits.DEBUG) {
            System.out.println("                [ Size = " + measurementCategories.size() + " ]");
        }

        for (Category measurementCategory : measurementCategories) {
            for (int measureIndex = 0; measureIndex < measurementCategory.getCount() + 1; measureIndex++) {
                line = scaner.nextLine();
//                System.out.println(line);
                valuesFromCategory.add(line);
            }
            measurements.add(new SimbadMeasurementsTable(measurementCategory, valuesFromCategory));
            valuesFromCategory.clear();
        }


//        if (Limits.DEBUG) { System.out.print("        Dividing measurements into: categories and values..."); }
//
//        Pattern pattern = Pattern.compile(RegularExpressions.SIMBAD_ASCII);
//        Matcher matcher = pattern.matcher(response);
//        if ( !matcher.matches() ) {
//            if (Limits.DEBUG) {
//                System.out.println("    [ FAIL ]");
//            }
//            return measurements;
//        }
//        String categories = matcher.group(1);
//        String values     = matcher.group(2);
//        if (Limits.DEBUG) {
//            System.out.println("    [ SUCCESS ]");
//        }
//
//
//
//        // load all values for each category
//        if (Limits.DEBUG) {
//            System.out.println("        Parsing values:");
////            System.out.println("        Whole:\n" + values);
//        }
//        int index = 0;
//        pattern = Pattern.compile(RegularExpressions.SIMBAD_ASCII_MEASURE_VAL);
//        matcher = pattern.matcher(values);
//        if (Limits.DEBUG && !measurementCategories.isEmpty()) {
//            System.out.println("            [ " + measurementCategories.get(index).getName() + " ]");
//        }
//
//        if ( !matcher.find() ) {
//            return measurements;
//        }
//
//        do {
//            if (Limits.DEBUG && index >= measurementCategories.size()) {
//                System.out.print("                " + matcher.group(0));
//            }
//
//            if ( !matcher.group(0).startsWith(measurementCategories.get(index).getName()) ) {
//                measurements.add(new SimbadMeasurementsTable(measurementCategories.get(index), valuesFromCategory));
//                valuesFromCategory.clear();
//                index++;
//
//                if (Limits.DEBUG && index < measurementCategories.size()) {
//                    System.out.println("            [ " + measurementCategories.get(index).getName() + " ]");
//                }
//            }
//
//            valuesFromCategory.add(matcher.group(0));
//            if (Limits.DEBUG) {
//                System.out.print("                " + matcher.group(0));
//            }
//
//            if (!matcher.find()) {
//                measurements.add(new SimbadMeasurementsTable(measurementCategories.get(index), valuesFromCategory));
//                valuesFromCategory.clear();
//                index++;
//
//                if (Limits.DEBUG && index < measurementCategories.size()) {
//                    System.out.println("            [ " + measurementCategories.get(index).getName() + " ]");
//                }
//                break;
//            }
//
//        } while (true);

        return measurements;
    }


}
