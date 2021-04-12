package org.astrosearcher.classes.vizier;

import cds.savot.model.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VizierTable {

    private String name;
    private String description;

    // fields, data
    private List<String> fields = new ArrayList<>();
    private List<List<String>> data = new ArrayList<>();

    public VizierTable(SavotTable table) {
        this.name        = table.getName();
        this.description = table.getDescription();

        FieldSet fieldset = table.getFields();
        for (int fieldIndex = 0; fieldIndex < fieldset.getItemCount(); fieldIndex++) {
            fields.add( ( (SavotField) fieldset.getItemAt(fieldIndex)).getName() );
        }

        TRSet rows = table.getData().getTableData().getTRs();
        for (int rowIndex = 0; rowIndex < rows.getItemCount(); rowIndex++) {

            List<String> nextRow = new ArrayList<>();
            TDSet columns = rows.getTDSet(rowIndex);

            for (int colIndex = 0; colIndex < columns.getItemCount(); colIndex++) {
                nextRow.add(((SavotTD)columns.getItemAt(colIndex)).getContent());
            }

            this.data.add(nextRow);
        }

    }


    public VizierTable(String name, String description, List<SavotField> responseFields, List<SavotTR> rows) {
        this.name        = name;
        this.description = description;

        for (SavotField field : responseFields) {
            fields.add(field.getName());
        }

        for (SavotTR row : rows) {

            List<String> nextRow = new ArrayList<>();
            TDSet columns = row.getTDSet();

            for (int colIndex = 0; colIndex < columns.getItemCount(); colIndex++) {
                nextRow.add(((SavotTD)columns.getItemAt(colIndex)).getContent());
            }

            this.data.add(nextRow);
        }
    }

    public static boolean isEmpty(List<SavotField> responseFields, List<SavotTR> rows) {
        return responseFields.isEmpty() || rows.isEmpty();
    }
}
