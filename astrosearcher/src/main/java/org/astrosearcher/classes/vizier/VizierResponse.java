package org.astrosearcher.classes.vizier;

import cds.savot.model.SavotField;
import cds.savot.model.SavotTD;
import cds.savot.model.SavotTR;
import cds.savot.model.TDSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.enums.vizier.VizierServices;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class VizierResponse {

    private VizierServices type;
    private List<String> fields = new ArrayList<>();
    private List<List<String>> data = new ArrayList<>();

    public VizierResponse(VizierServices service, List<SavotField> responseFields, List<SavotTR> data) {
        this.type = service;

        for (SavotField field : responseFields) {
            fields.add(field.getName());
        }

        for (SavotTR row : data) {

            List<String> nextRow = new ArrayList<>();
            TDSet columns = row.getTDSet();

            for (int colIndex = 0; colIndex < columns.getItemCount(); colIndex++) {
                nextRow.add(((SavotTD)columns.getItemAt(colIndex)).getContent());
            }

            this.data.add(nextRow);
        }
    }

    public boolean isEmpty() {
        return fields.isEmpty() || data.isEmpty();
    }
}
