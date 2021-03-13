package org.astrosearcher.classes.simbad;

import cds.savot.model.SavotField;
import cds.savot.model.SavotTD;
import cds.savot.model.SavotTR;
import cds.savot.model.TDSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.classes.constants.ExceptionMSG;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents parsed response from Simbad server.
 *
 * @author Ľuboslav Halama
 */

@NoArgsConstructor
@Getter
public class SimbadResponse {

    private List<SimbadFields> fields = new ArrayList<>();
    private List<List<String>> data = new ArrayList<>();;

    public SimbadResponse(List<SavotField> responseFields, List<SavotTR> data) {
        if (responseFields == null) {
            return;
        }

        for (SavotField field : responseFields) {
            try {
                fields.add(SimbadFields.valueOf(field.getId()));
            } catch (NullPointerException npe) {
                throw new NullPointerException(
                        ExceptionMSG.INVALID_SIMBAD_FIELD_NAME_EXCEPTION + npe
                );
            } catch (IllegalArgumentException iae) {
                throw new IllegalArgumentException(
                        ExceptionMSG.SIMBAD_FIELD_NAME_NOT_MATCHED_EXCEPTION + iae
                );
            }
        }

        for (SavotTR row : data) {
            List<String> columns = new ArrayList<>();
            TDSet responseColumns = row.getTDSet();

            for (int columnIndex = 0; columnIndex < responseColumns.getItemCount(); columnIndex++) {
                columns.add(((SavotTD)responseColumns.getItemAt(columnIndex)).getContent());
            }

            this.data.add(columns);
        }

//        this.data = data;
        System.out.println("Data size: " + this.data.size());
//        for (var stg : this.data.get(0).) {
//            System.out.println("    line: " + stg);
//        }

//        System.out.println("Data: " + this.data.get(0).getTDarray());
    }

    public boolean isEmpty() {
        // TODO: implement this method
        return fields.isEmpty() || data.isEmpty();
    }
}
