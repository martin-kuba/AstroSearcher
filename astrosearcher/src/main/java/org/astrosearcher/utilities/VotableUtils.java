package org.astrosearcher.utilities;

import cds.savot.model.SavotTable;
import cds.savot.model.TRSet;

/**
 * Class offers functionality for working with parsed votable data. .
 *
 * @author Ľuboslav Halama
 */
public class VotableUtils {

    public static boolean isEmpty(SavotTable table) {
        if (table == null) {
            return true;
        }

        // If there are no fields (columns info)
        if (table.getFields() == null || table.getFields().getItemCount() == 0) {
            return true;
        }

        // If there is no data section
        if (table.getData() == null || table.getData().getTableData() == null) {
            return true;
        }

        // If there are no rows with data
        return isEmpty(table.getData().getTableData().getTRs());

    }

    public static boolean isEmpty(TRSet rows) {
        return rows == null || rows.getItemCount() == 0;
    }
}
