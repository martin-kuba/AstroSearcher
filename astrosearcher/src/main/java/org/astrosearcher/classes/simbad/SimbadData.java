package org.astrosearcher.classes.simbad;

import cds.savot.model.SavotTD;
import cds.savot.model.TDSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astrosearcher.classes.constants.SimbadConstants;
import org.astrosearcher.classes.constants.VizierConstants;
import org.astrosearcher.enums.simbad.SimbadArgType;
import org.astrosearcher.enums.simbad.SimbadFields;
import org.astrosearcher.enums.simbad.SimbadServices;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Setter
public class SimbadData {
    private String queried;
    private String mainID;
    private String objectType;
    private int    references;

    private String spectralType;
    private String morphologicalType;

    private String radialVelocity;
    private String redshift;
    private String effectiveTemperature;

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

    private Map<String, SimbadFlux> fluxes = new HashMap<>();

    private List<SimbadFields> unassignedFields = new ArrayList<>();
    private List<String>       unassignedData   = new ArrayList<>();

    public SimbadData(TDSet columns, List<SimbadFields> fields) {
        int columnIndex = 0;
        while (columnIndex < columns.getItemCount()) {

//            System.out.println(fields.get(columnIndex) + " [" + columnIndex + "]: " +
//                    ((SavotTD)columns.getItemAt(columnIndex)).getContent());

            switch (fields.get(columnIndex)) {
                case TYPED_ID:
                    queried = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case MAIN_ID:
                    mainID = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case OTYPE_S:
                    objectType = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case NB_REF:
                    references = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? 0
                            : Integer.parseInt(((SavotTD)columns.getItemAt(columnIndex)).getContent());
                    break;

                case SP_TYPE:
                    spectralType = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case MORPH_TYPE:
                    morphologicalType = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;

                case RV_VALUE:
                    radialVelocity = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                            : ((SavotTD)columns.getItemAt(columnIndex)).getContent() + " [ km/s ]";
                    break;
                case Z_VALUE:
                    redshift = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case Fe_H_Teff:
                    effectiveTemperature = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                                               ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                                               : ((SavotTD)columns.getItemAt(columnIndex)).getContent() + " [ K ]";
                    break;

                case COO_ERR_MAJA_d:
                    coordErrorMajA = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case COO_ERR_MINA_d:
                    coordErrorMinA = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case COO_ERR_ANGLE_d:
                    coordErrorAng = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case RA_d:
                    ra = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case DEC_d:
                    dec = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;

                case PM_pmra:
                    pmra = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                               ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                               : ((SavotTD)columns.getItemAt(columnIndex)).getContent() + " [ mas/yr ]";
                    break;
                case PM_pmde:
                    pmdec = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                            : ((SavotTD)columns.getItemAt(columnIndex)).getContent() + " [ mas/yr ]";
                    break;
                case PM_ERR_MAJA:
                    pmErrorMajA = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case PM_ERR_MINA:
                    pmErrorMinA = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case PM_ERR_ANGLE:
                    pmErrorAng = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case PM_system:
                    pmSystem = !((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? "(" + ((SavotTD)columns.getItemAt(columnIndex)).getContent() +")"
                            : "";
                    break;
                case PM_bibcode:
                    pmBibcode = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
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
                    if ( !((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()) {
                        SimbadFlux flux = new SimbadFlux(columns, columnIndex);
                        fluxes.put(flux.getFilter(), flux);
                    }
                    columnIndex += 8;
                    break;

                case PLX_plx:
                    parallaxValue = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case PLX_me:
                    parallaxError = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case PLX_R:
                    parallaxObsCode = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case PLX_bibcode:
                    parallaxBibcode = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;

                default:
                    unassignedFields.add(fields.get(columnIndex));
                    unassignedData.add(((SavotTD)columns.getItemAt(columnIndex)).getContent());

            }

            ++columnIndex;
        }

    }

    public String getMainIdUrlEncoded() {
        return URLEncoder.encode(mainID, StandardCharsets.UTF_8);
    }

    public String getCDSPortalUrl() {
        return SimbadConstants.CDS_PORTAL_UrL + "?target=" + getMainIdUrlEncoded();
    }

    public String getReferencesUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_ID_REFERENCE +
                "?" + SimbadArgType.ID + "=" + URLEncoder.encode(mainID, StandardCharsets.UTF_8);
    }

    public String getPMBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(pmBibcode, StandardCharsets.UTF_8);
    }

    public String getParallaxBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(parallaxBibcode, StandardCharsets.UTF_8);
    }

    public String getObjectTypeUrl() {
        return VizierConstants.OBJECT_TYPE_URL;
    }

    public String getQueryUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_ID
                + "?" + SimbadArgType.ID + "=" + URLEncoder.encode(mainID, StandardCharsets.UTF_8);
    }
}
