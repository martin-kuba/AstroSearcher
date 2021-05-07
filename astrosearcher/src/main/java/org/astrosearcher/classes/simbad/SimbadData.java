package org.astrosearcher.classes.simbad;

import Coordinate_Converter.astroj.SkyAlgorithms;
import cds.savot.model.SavotTD;
import cds.savot.model.TDSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.enums.cds.simbad.SimbadArgType;
import org.astrosearcher.enums.cds.simbad.SimbadFields;
import org.astrosearcher.enums.cds.simbad.SimbadServices;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class represents parsed data from Simbad response.
 *
 * Data are being assigned by current field in each iteration.
 *
 * @author Ľuboslav Halama
 */
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

    private String angularDistance;
    private String angularSizeMajor;
    private String angularSizeMinor;
    private String angularSizeAngle;

    private String radialVelocity;
    private String redshift;
    private String effectiveTemperature;

    private String inputRA = null;
    private String inputDec = null;

    private String ra;
    private String dec;
    private String coordErrorMajA;
    private String coordErrorMinA;
    private String coordErrorAng;

    private Double galacticLongitude;
    private Double galacticLatitude;

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
    private List<SimbadMeasurementsTable> measurements = new ArrayList<>();

    private List<SimbadFields> unassignedFields = new ArrayList<>();
    private List<String>       unassignedData   = new ArrayList<>();

    public SimbadData(TDSet columns, List<SimbadFields> fields) {
        int columnIndex = 0;
        while (columnIndex < columns.getItemCount()) {

            switch (fields.get(columnIndex)) {
                case TYPED_ID:
                    queried = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case main_id:
                case MAIN_ID:
                    mainID = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case main_type:
                case OTYPE_S:
                    objectType = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case nbref:
                case NB_REF:
                    references = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? 0
                            : Integer.parseInt(((SavotTD)columns.getItemAt(columnIndex)).getContent());
                    break;

                case sp_type:
                case SP_TYPE:
                    spectralType = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case morph_type:
                case MORPH_TYPE:
                    morphologicalType = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;

                case angDist:
                case ANG_DIST:
                    angularDistance = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                            : ((SavotTD)columns.getItemAt(columnIndex)).getContent() + " [ arcsec ]";
                    break;
                case size_maj:
                case GALDIM_MAJAXIS:
                    angularSizeMajor = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case size_min:
                case GALDIM_MINAXIS:
                    angularSizeMinor = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case size_angle:
                case GALDIM_ANGLE:
                    angularSizeAngle = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;

                case radvel:
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

                case coo_err_maj:
                case COO_ERR_MAJA_d:
                    coordErrorMajA = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case coo_err_min:
                case COO_ERR_MINA_d:
                    coordErrorMinA = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case coo_err_angle:
                case COO_ERR_ANGLE_d:
                    coordErrorAng = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case ra:
                case RA_d:
                    if ( ra != null && !ra.isEmpty()) {
                        inputRA = ra;
                    }
                    ra = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case dec:
                case DEC_d:
                    if ( dec != null && !dec.isEmpty()) {
                        inputDec = dec;
                    }
                    dec = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;

                case pmra:
                case PMRA:
                    pmra = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                               ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                               : ((SavotTD)columns.getItemAt(columnIndex)).getContent();// + " [ mas/yr ]";
                    break;
                case pmdec:
                case PMDEC:
                    pmdec = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                            : ((SavotTD)columns.getItemAt(columnIndex)).getContent(); // + " [ mas/yr ]";
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

                case B:
                case V:
                case R:
                case J:
                case H:
                case K:
                case u:
                case g:
                case r:
                case i:
                case z:
                    if ( !((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()) {
                        SimbadFlux flux = new SimbadFlux(
                                fields.get(columnIndex).name(),
                                Double.parseDouble(((SavotTD)columns.getItemAt(columnIndex)).getContent())
                        );
                        fluxes.put(flux.getFilter(), flux);
                    }
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

                case plx:
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

        calculate_galactic_coords();

    }

    private void calculate_galactic_coords() {

        if (AppConfig.DEBUG) {
            System.out.println("        Starting the calculation of galactic coordinates.");
        }

        double ra_double;
        double dec_double;
        double pmra_double = 0;
        double pmdec_double = 0;
        boolean usePM = true;

        // get RA and DEC
        try {
            ra_double  = Double.parseDouble(this.ra);
            dec_double = Double.parseDouble(this.dec);
        } catch (NumberFormatException nfe) {
            System.err.println("Exception caught while calculating galactic coordinates (NumberFormatException):\n"
                    + nfe.getMessage());
            return;
        }

        // get Proper Motion if possible, dont use it otherwise
        try {
            pmra_double  = Double.parseDouble(this.pmra);
            pmdec_double = Double.parseDouble(this.pmdec);
        } catch (NumberFormatException nfe) {
            usePM = false;
        }

        // calculate galactic coordinates and store them
        double[] results = SkyAlgorithms.J2000toGal(ra_double / 15, dec_double, pmra_double, pmdec_double, usePM);
        galacticLongitude = results[0];
        galacticLatitude  = results[1];
    }

    /**
     * Encodes object’s ID into URL format
     *
     * @return  URL encoded object’s ID
     */
    public String getMainIdUrlEncoded() {
        return URLEncoder.encode(mainID, StandardCharsets.UTF_8);
    }

    /**
     * Result from function is used for proper generation of links for CDS Portal.
     *
     * @return  constructed URL for CDS Portal (with given object ID settings)
     */
    public String getCDSPortalUrl() {
        return SimbadConstants.CDS_PORTAL_UrL + "?target=" + getMainIdUrlEncoded();
    }

    /**
     * Result from function is used for proper generation of links for Simbad object ID reference query.
     *
     * @return  constructed URL for Simbad query by object ID reference (bibcode)
     */
    public String getReferencesUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_ID_REFERENCE +
                "?" + SimbadArgType.ID + "=" + URLEncoder.encode(mainID, StandardCharsets.UTF_8);
    }

    /**
     * Result from function is used for proper generation of links for Simbad object Proper Motion reference query.
     *
     * @return  constructed URL for Simbad query by object Proper Motion reference (bibcode)
     */
    public String getPMBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(pmBibcode, StandardCharsets.UTF_8);
    }

    /**
     * Result from function is used for proper generation of links for Simbad object parallax reference query.
     *
     * @return  constructed URL for Simbad query by object Parallax reference (bibcode)
     */
    public String getParallaxBibcodeUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_REFERENCE
                + "?bibcode=" + URLEncoder.encode(parallaxBibcode, StandardCharsets.UTF_8);
    }

    /**
     * Result from function is used for proper generation of links for Simbad ID query.
     *
     * @return  constructed URL for Simbad query by object ID
     */
    public String getQueryUrl() {
        return SimbadConstants.CONNECTION_URL + SimbadServices.SIMBAD_ID
                + "?" + SimbadArgType.ID + "=" + URLEncoder.encode(mainID, StandardCharsets.UTF_8);
    }
}
