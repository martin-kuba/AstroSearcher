package org.astrosearcher.classes.simbad;

import Coordinate_Converter.astroj.SkyAlgorithms;
import cds.savot.model.SavotTD;
import cds.savot.model.TDSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.SimbadConstants;
import org.astrosearcher.classes.constants.VizierConstants;
import org.astrosearcher.enums.simbad.SimbadArgType;
import org.astrosearcher.enums.simbad.SimbadFields;
import org.astrosearcher.enums.simbad.SimbadServices;
import org.ejml.simple.SimpleMatrix;

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

    private String angularDistance;
    private String angularSizeMajor;
    private String angularSizeMinor;
    private String angularSizeAngle;

    private String radialVelocity;
    private String redshift;
    private String effectiveTemperature;

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

                case ANG_DIST:
                    angularDistance = ((SavotTD)columns.getItemAt(columnIndex)).getContent().isEmpty()
                            ? ((SavotTD)columns.getItemAt(columnIndex)).getContent()
                            : ((SavotTD)columns.getItemAt(columnIndex)).getContent() + " [ arcsec ]";
                    break;
                case GALDIM_MAJAXIS:
                    angularSizeMajor = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case GALDIM_MINAXIS:
                    angularSizeMinor = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
                    break;
                case GALDIM_ANGLE:
                    angularSizeAngle = ((SavotTD)columns.getItemAt(columnIndex)).getContent();
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
                               : ((SavotTD)columns.getItemAt(columnIndex)).getContent();// + " [ mas/yr ]";
                    break;
                case PM_pmde:
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

        calculate_galactic_coords();

    }

    private void calculate_galactic_coords() {

        if (Limits.DEBUG) {
            System.out.println("        Starting the calculation of galactic coordinates.");
        }

        double ra_double;
        double dec_double;
        double pmra_double = 0;
        double pmdec_double = 0;
        boolean usePM = true;

        try {
            ra_double  = Double.parseDouble(this.ra);
            dec_double = Double.parseDouble(this.dec);
        } catch (NumberFormatException nfe) {
            System.err.println("Exception caught while calculating galactic coordinates (NumberFormatException):\n"
                    + nfe.getMessage());
            return;
        }
        try {
            pmra_double  = Double.parseDouble(this.pmra);
            pmdec_double = Double.parseDouble(this.pmdec);
        } catch (NumberFormatException nfe) {
            usePM = false;
        }

        double[] results = SkyAlgorithms.J2000toGal(ra_double / 15, dec_double, pmra_double, pmdec_double, usePM);
        galacticLongitude = results[0];
        galacticLatitude  = results[1];

//        System.out.println("    RA and DEC parsed.");
//
//        double theta = Math.toRadians(ra_double);    // Convert from deg to radians
//        double phi   = Math.toRadians(dec_double);   // Convert from deg to radians
//
//        System.out.println("    RA and DEC converted to radians.");
//
//        System.out.println("    Calculating latitude...");
//        galacticLatitude = Math.asin(
//                Math.sin(phi) * Math.cos(Limits.delta_zero_rad)
//                - Math.cos(phi) * Math.sin(theta - Limits.alpha_zero_rad) * Math.sin(Limits.delta_zero_rad)
//        );
//
//        System.out.println("    Calculating longitude...");
//        galacticLongitude = Math.acos(
//                Math.cos(phi) * Math.cos(theta - Limits.alpha_zero_rad)
//                / Math.cos(galacticLatitude)
//        );

//        System.out.println();
//        System.out.println("    Converting longitude and latitude to degrees...");
//        galacticLongitude = Math.toDegrees(galacticLongitude);
//        galacticLatitude  = Math.toDegrees(galacticLatitude);


//
//        // Convert from spherical to cartesian coordinates system
//        double x = Math.cos(theta) * Math.cos(phi);
//        double y = Math.sin(theta) * Math.cos(phi);
//        double z = Math.sin(phi);
//
//        // initialize matrices
//        SimpleMatrix coords  = new SimpleMatrix(new double[][] {{x},{y},{z}});
//
//        SimpleMatrix rotate1 = new SimpleMatrix(new double[][] {
//                {Math.cos(Limits.longitude_zero_rad),  Math.sin(Limits.longitude_zero_rad), 0},
//                {-Math.sin(Limits.longitude_zero_rad), Math.cos(Limits.longitude_zero_rad), 0},
//                {0, 0, 1}
//        });
//
//        SimpleMatrix rotate2 = new SimpleMatrix(new double[][] {
//                {1, 0, 0},
//                {0, Math.cos(Limits.delta_zero_rad),  Math.sin(Limits.delta_zero_rad)},
//                {0, -Math.sin(Limits.delta_zero_rad), Math.cos(Limits.delta_zero_rad)}
//        });
//
//        SimpleMatrix rotate3 = new SimpleMatrix(new double[][] {
//                {Math.cos(Limits.alpha_zero_rad),  Math.sin(Limits.alpha_zero_rad), 0},
//                {-Math.sin(Limits.alpha_zero_rad), Math.cos(Limits.alpha_zero_rad), 0},
//                {0, 0, 1}
//        });
//
//        // rotate
//        SimpleMatrix result = rotate1.mult(rotate2).mult(rotate3).mult(coords);
//        System.out.println("Matrix:\n" + rotate1.mult(rotate2).mult(rotate3));
//
//        // get new cartesian coordinates from result matrix
//        x = result.get(0, 0);
//        y = result.get(1, 0);
//        z = result.get(2, 0);
//
//        // convert from cartesian to spherical coodinate system
//        theta = Math.atan2(y, x);
//        phi   = Math.acos( z / Math.sqrt(x*x + y*y + z*z));
//
//        galacticLatitude = Math.asin(
//                Math.sin(phi) * Math.cos(Limits.delta_zero_rad))
//                - Math.cos(phi) * Math.sin(theta - Limits.alpha_zero_rad) * Math.sin(Limits.delta_zero_rad
//        );
//
//        galacticLatitude = Math.asin(
//                Math.sin(phi) * Math.cos(Limits.delta_zero_rad)
//                - Math.cos(phi) * Math.sin(theta - Limits.alpha_zero_rad) * Math.sin(Limits.delta_zero_rad)
//        );
//        galacticLongitude = Math.acos(Math.cos(phi) * Math.cos(theta - Limits.alpha_zero_rad)
//                / Math.cos(galacticLatitude)) + 33 * Math.PI / 180;
//
//        galacticLatitude   = galacticLatitude  * 180 / Math.PI;  // convert from radians to deg
//        galacticLongitude  = galacticLongitude * 180 / Math.PI;  // convert from radians to deg
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
