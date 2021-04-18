package org.astrosearcher.classes.vizier;

import cds.savot.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.enums.cds.vizier.VizierServices;
import org.astrosearcher.utilities.VotableUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class VizierResponse {

    private VizierServices type;
    private List<String> fields = new ArrayList<>();
    private List<List<String>> data = new ArrayList<>();

    private List<VizierTable> tables = new ArrayList<>();

    public VizierResponse(VizierServices service, ResourceSet resources) {
        this.type = service;

        int debug_progress_counter = 0;
        if (Limits.DEBUG) {
            System.out.println("        Parsing the response...");
        }

        // load and check every resource in response
        for (int resIndex = 0; resIndex < resources.getItemCount(); resIndex++) {
            SavotResource resource = (SavotResource) resources.getItemAt(resIndex);

            // load and check every table from current resource
            for (int tableIndex = 0; tableIndex < resource.getTableCount(); tableIndex++) {
                SavotTable table = (SavotTable) resource.getTables().getItemAt(tableIndex);

                // if table is not empty, store it into: List<VizierTable> tables
                if ( !VotableUtils.isEmpty(table) ) {
//                    tables.add(new VizierTable(
//                            table.getName(),
//                            table.getDescription(),
//                            table.getFields().getItems(),
//                            resource.getTRSet(tableIndex).getItems()
////                            table.getData().getTableData().getTRs().getItems()
//                    ));
                    tables.add(new VizierTable(table));
                }
            }

            if (Limits.DEBUG && resIndex >= (debug_progress_counter + 1) * resources.getItemCount() / 4) {
                debug_progress_counter++;
                System.out.println("            [  " + debug_progress_counter * 25 + "% ] resources parsed!");
            }
        }

        if (Limits.DEBUG) {
            System.out.println("            [ 100% ] resources parsed!");
        }

    }

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
        return tables.isEmpty();
//        return fields.isEmpty() || data.isEmpty();
    }
}
