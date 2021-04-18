package org.astrosearcher.classes.vizier;

import cds.savot.model.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class one table from Vizier response
 *
 * Class stores:
 *   1.) name of the table
 *   2.) description of the table
 *   3.) fields (column names in table)
 *   4.) data from table
 *
 * @author Ľuboslav Halama
 */
@Getter
public class VizierTable {

    private String name;
    private String description;

    private List<String>       fields = new ArrayList<>();
    private List<List<String>> data = new ArrayList<>();

    public VizierTable(SavotTable table) {
        this.name        = table.getName();
        this.description = table.getDescription();

        // store fields
        FieldSet fieldset = table.getFields();
        for (int fieldIndex = 0; fieldIndex < fieldset.getItemCount(); fieldIndex++) {
            fields.add( ( (SavotField) fieldset.getItemAt(fieldIndex)).getName() );
        }

        // get data (rows)
        TRSet rows = table.getData().getTableData().getTRs();
        for (int rowIndex = 0; rowIndex < rows.getItemCount(); rowIndex++) {

            List<String> nextRow = new ArrayList<>();
            TDSet columns = rows.getTDSet(rowIndex);

            // For each column, get its content (data) and store it
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
