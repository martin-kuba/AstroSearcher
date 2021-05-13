package org.astrosearcher.classes.simbad;

import cds.savot.model.LinkSet;
import cds.savot.model.SavotField;
import cds.savot.model.SavotLink;
import cds.savot.model.SavotTR;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.enums.cds.simbad.SimbadFields;
import org.astrosearcher.enums.cds.simbad.SimbadServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents parsed response from Simbad server.
 *
 * Class stores:
 *   1.) type of query
 *   2.) fields (column names in table)
 *   3.) data (parsed separately using SimbadData class)
 *   4.) Units for data
 *
 * @author Ľuboslav Halama
 */
@NoArgsConstructor
@Getter
public class SimbadResponse {

    private static final Logger log = LoggerFactory.getLogger(SimbadResponse.class);

    private SimbadServices     type;
    private List<SimbadFields> fields       = new ArrayList<>();
    private List<SimbadData>   assignedData = new ArrayList<>();

    private String objectTypeDescLink = "";

    private String raUnit = "";
    private String decUnit = "";
    private String coordErrorMajAUnit = "";
    private String coordErrorMinAUnit = "";
    private String coordErrorAngUnit = "";

    private String pmraUnit = "";
    private String pmdecUnit = "";

    private String galLongitudeUnit = "[ deg ]";
    private String galLatitudeUnit = "[ deg ]";

    public SimbadResponse(SimbadServices type, List<SavotField> responseFields, List<SavotTR> data) {
        this.type = type;

        if (responseFields == null) {
            return;
        }

        // parse fields
        for (SavotField field : responseFields) {
            try {
                fields.add( field.getId() == null || field.getId().isEmpty()
                        ? SimbadFields.valueOf(field.getName())
                        : SimbadFields.valueOf(field.getId())
                );
                assignFieldInfoIfRequired(field);
            } catch (NullPointerException npe) {
                throw new NullPointerException(
                        ExceptionMSG.INVALID_SIMBAD_FIELD_NAME_EXCEPTION + npe
                );
            } catch (IllegalArgumentException iae) {
                throw new IllegalArgumentException(
                        "Field: '" + (field.getId()  == null ? field.getName() : field.getId())
                                + "' " +
                        ExceptionMSG.SIMBAD_FIELD_NAME_NOT_MATCHED_EXCEPTION + iae
                );
            }
        }

        // parse each row of data and store it
        for (SavotTR row : data) {
            assignedData.add(new SimbadData(row.getTDSet(), fields));
        }

        log.debug("        Simbad response:");
        for (int i = 0; i < fields.size(); i++) {
            log.debug("            [ " + fields.get(i) + " ]: " + data.get(0).getTDSet().getContent(i));
        }
    }

    /**
     * Function takes field and decides, whether unit must be stored.
     * If so, unit is obtained and stored.
     *
     * @param field    field from VOTable file
     */
    private void assignFieldInfoIfRequired(SavotField field) {

        // Right Ascension
        if (field.getId().equals(SimbadFields.RA_d.name())
                || field.getName().equals(SimbadFields.ra.name())) {
            raUnit = " [ " + field.getUnit() + " ]";
        }

        // Declination
        if (field.getId().equals(SimbadFields.DEC_d.name())
                || field.getName().equals(SimbadFields.dec.name())) {
            decUnit = " [ " + field.getUnit() + " ]";
        }

        if (field.getId().equals(SimbadFields.COO_ERR_MAJA_d.name())
                || field.getName().equals(SimbadFields.coo_err_maj.name())) {
            coordErrorMajAUnit = " [ " + field.getUnit() + " ]";
        }

        if (field.getId().equals(SimbadFields.COO_ERR_MINA_d.name())
                || field.getName().equals(SimbadFields.coo_err_min.name())) {
            coordErrorMinAUnit = " [ " + field.getUnit() + " ]";
        }

        if (field.getId().equals(SimbadFields.COO_ERR_ANGLE_d.name())
                || field.getName().equals(SimbadFields.coo_err_angle.name())) {
            coordErrorAngUnit = " [ " + field.getUnit() + " ]";
        }

        // Proper Motion - Right Ascension
        if (field.getId().equals(SimbadFields.PM_pmra.name())
                || field.getName().equals(SimbadFields.pmra.name())) {
            pmraUnit = " [ " + field.getUnit() + " ]";
        }

        // Proper Motion - Declination
        if (field.getId().equals(SimbadFields.PM_pmde.name())
                || field.getName().equals(SimbadFields.pmdec.name()) ) {
            pmdecUnit = " [ " + field.getUnit() + " ]";
        }

        // type of astronomical object
        if (field.getId().equals(SimbadFields.OTYPE_S.name())
                || field.getName().equals(SimbadFields.main_type.name())) {
            LinkSet links = field.getLinks();
            objectTypeDescLink = links.getItemCount() == 0
                    ? SimbadConstants.OBJECT_TYPE_URL
                    : ((SavotLink)links.getItemAt(0)).getHref();
        }
    }

    public boolean isEmpty() {
        // TODO: implement this method
        return fields.isEmpty() || assignedData.isEmpty(); //|| data.isEmpty();
    }
}
