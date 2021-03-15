package org.astrosearcher.classes.simbad;

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

    public String getBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(bibcode, StandardCharsets.UTF_8);
    }
}
