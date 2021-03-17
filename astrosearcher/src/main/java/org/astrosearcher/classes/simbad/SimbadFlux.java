package org.astrosearcher.classes.simbad;

import cds.savot.model.SavotTD;
import cds.savot.model.TDSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.astrosearcher.classes.constants.SimbadConstants;
import org.astrosearcher.classes.constants.VizierConstants;
import org.astrosearcher.enums.simbad.SimbadServices;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@AllArgsConstructor
@Getter
public class SimbadFlux {
    private String filter;
    private double magnitude;
    private double error;
    private String system;
    private String bibcode;  // reference
    private String variabilityFlag;
    private String multiplicityFlag;
    private String qualityFlag;
    private String unit;

    public SimbadFlux(List<String> row, int startIndex) {
        filter    = row.get(startIndex);
        magnitude = Double.parseDouble(row.get(startIndex + 1));
        error     = row.get(startIndex + 2).isEmpty() ? 0 : Double.parseDouble(row.get(startIndex + 2));
        system    = row.get(startIndex + 3);
        bibcode   = row.get(startIndex + 4);

        variabilityFlag  = row.get(startIndex + 5);
        multiplicityFlag = row.get(startIndex + 6);
        qualityFlag      = row.get(startIndex + 7);

        unit = row.get(startIndex + 8);
    }

    public SimbadFlux(TDSet columns, int startIndex) {
        filter    = ((SavotTD)columns.getItemAt(startIndex)).getContent();
        magnitude = Double.parseDouble(((SavotTD)columns.getItemAt(startIndex + 1)).getContent());
        error     = ((SavotTD)columns.getItemAt(startIndex + 2)).getContent().isEmpty()
                        ? 0
                        : Double.parseDouble(((SavotTD)columns.getItemAt(startIndex + 2)).getContent());
        system    = ((SavotTD)columns.getItemAt(startIndex+3)).getContent();
        bibcode   = ((SavotTD)columns.getItemAt(startIndex + 4)).getContent();

        variabilityFlag  = ((SavotTD)columns.getItemAt(startIndex + 5)).getContent();
        multiplicityFlag = ((SavotTD)columns.getItemAt(startIndex + 6)).getContent();
        qualityFlag      = ((SavotTD)columns.getItemAt(startIndex + 7)).getContent();

        unit = ((SavotTD)columns.getItemAt(startIndex + 8)).getContent();

    }

    public String getBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(bibcode, StandardCharsets.UTF_8);
    }
}
