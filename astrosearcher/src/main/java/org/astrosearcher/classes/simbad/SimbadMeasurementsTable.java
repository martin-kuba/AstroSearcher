package org.astrosearcher.classes.simbad;

import lombok.Getter;
import org.astrosearcher.utilities.SimbadASCIIParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SimbadMeasurementsTable {

    private String name;
    private int count;
    List<String> columnNames;
    List<List<String>> values;

    public SimbadMeasurementsTable(SimbadASCIIParser.Category measurementCategory, List<String> valuesFromCategory) {
        name = measurementCategory.getName();
        count = measurementCategory.getCount();
        values = new ArrayList<>();

        columnNames = Arrays.stream(valuesFromCategory.get(0)
                .split("\\|"))
                .map((colName) -> colName = colName.trim())
                .collect(Collectors.toList());
        valuesFromCategory.remove(0);

        for (String valuesSet : valuesFromCategory) {

            values.add(Arrays.stream(valuesSet
                    .split("\\|"))
                    .map((value) -> value = value.trim())
                    .collect(Collectors.toList()));
        }
    }
}
