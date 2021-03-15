package org.astrosearcher.classes.simbad;

import cds.savot.model.SavotField;
import cds.savot.model.SavotTR;
import lombok.Getter;
import org.astrosearcher.classes.constants.SimbadConstants;
import org.astrosearcher.classes.constants.VizierConstants;
import org.astrosearcher.enums.simbad.SimbadArgType;
import org.astrosearcher.enums.simbad.SimbadFields;
import org.astrosearcher.enums.simbad.SimbadServices;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SimbadResponseForId extends SimbadResponse {
    private String queried;
    private String mainID;
    private String objectType;
    private int    references;

    private String spectralType;
    private String morphologicalType;

    private String ra;
    private String dec;
    private String coordErrorMajA;
    private String coordErrorMinA;
    private String coordErrorAng;

    private String pmra;
    private String pmdec;
    private String pmErrorMajA;
    private String pmErrorMinA;
    private String pmErrorAng;
    private String pmSystem;
    private String pmBibcode;

    private String parallaxValue;
    private String parallaxError;
    private String parallaxObsCode;
    private String parallaxBibcode;

    private List<SimbadFlux> fluxes = new ArrayList<>();

    private List<SimbadFields> unassignedFields = new ArrayList<>();
    private List<String>       unassignedData   = new ArrayList<>();

    public SimbadResponseForId(SimbadServices type, List<SavotField> responseFields, List<SavotTR> data) {
        super(type, responseFields, data);

        if (this.data.isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("**************");
        System.out.println(" DATA PARSING ");
        System.out.println("**************");
        System.out.println();
        // There is only one row in response for query by ID
        List<String> row = this.data.get(0);
        int columnIndex = 0;
        while (columnIndex < row.size()) {

            System.out.println(fields.get(columnIndex) + " [" + columnIndex + "]: " + row.get(columnIndex));

            switch (fields.get(columnIndex)) {
                case TYPED_ID:
                    queried = row.get(columnIndex);
                    break;
                case MAIN_ID:
                    mainID = row.get(columnIndex);
                    break;
                case OTYPE_S:
                    objectType = row.get(columnIndex);
                    break;
                case NB_REF:
                    references = row.get(columnIndex).isEmpty() ? 0 : Integer.parseInt(row.get(columnIndex));
                    break;

                case SP_TYPE:
                    spectralType = row.get(columnIndex);
                    break;
                case MORPH_TYPE:
                    morphologicalType = row.get(columnIndex);
                    break;

                case COO_ERR_MAJA_d:
                    coordErrorMajA = row.get(columnIndex);
                    break;
                case COO_ERR_MINA_d:
                    coordErrorMinA = row.get(columnIndex);
                    break;
                case COO_ERR_ANGLE_d:
                    coordErrorAng = row.get(columnIndex);
                    break;
                case RA_d:
                    ra = row.get(columnIndex);
                    break;
                case DEC_d:
                    dec = row.get(columnIndex);
                    break;

                case PM_pmra:
                    pmra = row.get(columnIndex);
                    break;
                case PM_pmde:
                    pmdec = row.get(columnIndex);
                    break;
                case PM_ERR_MAJA:
                    pmErrorMajA = row.get(columnIndex);
                    break;
                case PM_ERR_MINA:
                    pmErrorMinA = row.get(columnIndex);
                    break;
                case PM_ERR_ANGLE:
                    pmErrorAng = row.get(columnIndex);
                    break;
                case PM_system:
                    pmSystem = !row.get(columnIndex).isEmpty() ? "(" + row.get(columnIndex) +")" : "";
                    break;
                case PM_bibcode:
                    pmBibcode = row.get(columnIndex);
                    break;

                case FILTER_NAME_U:
                case FILTER_NAME_B:
                case FILTER_NAME_V:
                case FILTER_NAME_R:
                case FILTER_NAME_I:
                case FILTER_NAME_J:
                case FILTER_NAME_H:
                case FILTER_NAME_K:
                case FILTER_NAME_u:
                case FILTER_NAME_g:
                case FILTER_NAME_r:
                case FILTER_NAME_i:
                case FILTER_NAME_z:
                    if ( !row.get(columnIndex).isEmpty()) { fluxes.add(new SimbadFlux(row, columnIndex)); }
                    columnIndex += 8;
                    break;

                case PLX_plx:
                    parallaxValue = row.get(columnIndex);
                    break;
                case PLX_me:
                    parallaxError = row.get(columnIndex);
                    break;
                case PLX_R:
                    parallaxObsCode = row.get(columnIndex);
                    break;
                case PLX_bibcode:
                    parallaxBibcode = row.get(columnIndex);
                    break;

                default:
                    unassignedFields.add(fields.get(columnIndex));
                    unassignedData.add(row.get(columnIndex));

            }

            ++columnIndex;
        }

    }

    public String getObjectTypeUrl() {
        return VizierConstants.OBJECT_TYPE_URL;
    }

    public String getPMBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(pmBibcode, StandardCharsets.UTF_8);
    }

    public String getParallaxBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(parallaxBibcode, StandardCharsets.UTF_8);
    }

    public String getReferencesUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_ID_REFERENCE +
                "?" + SimbadArgType.ID + "=" + mainID;
    }
}
