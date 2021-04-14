package org.astrosearcher.classes.simbad;

import cds.savot.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.SimbadConstants;
import org.astrosearcher.classes.constants.VizierConstants;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.enums.simbad.SimbadFields;
import org.astrosearcher.enums.simbad.SimbadServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class represents parsed response from Simbad server.
 *
 * @author Ľuboslav Halama
 */

@NoArgsConstructor
@Getter
public class SimbadResponse {

    private SimbadServices type;
    List<SimbadFields> fields = new ArrayList<>();
    private Map<SimbadFields, Integer> fieldMapper = new HashMap<>();
    List<List<String>> data = new ArrayList<>();

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

    List<SimbadData> assignedData = new ArrayList<>();

    public SimbadResponse(SimbadServices type, List<SavotField> responseFields, List<SavotTR> data) {
        this.type = type;

        if (responseFields == null) {
            return;
        }

        int order = 0;

        for (SavotField field : responseFields) {
            try {
//                System.out.println("        Field: " + field.getName() + ", " + field.getId());
                fields.add( field.getId() == null || field.getId().isEmpty()
                        ? SimbadFields.valueOf(field.getName())
                        : SimbadFields.valueOf(field.getId())
                );
                assignFieldInfoIfRequired(field);
//                fieldMapper.put(SimbadFields.valueOf(field.getId()), order);
//                ++order;
            } catch (NullPointerException npe) {
                throw new NullPointerException(
                        ExceptionMSG.INVALID_SIMBAD_FIELD_NAME_EXCEPTION + npe
                );
            } catch (IllegalArgumentException iae) {
//                if (Limits.DEBUG) {
//                    System.err.println("Field '" + field.getId() + "' not found in pre-defined Simbad fields enum");
//                }



                throw new IllegalArgumentException(
                        "Field: '" + (field.getId()  == null ? field.getName() : field.getId())
                                + "' " +
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

            assignedData.add(new SimbadData(responseColumns, fields));
            this.data.add(columns);
        }

        if (Limits.DEBUG && Limits.DEBUG_DISPLAY_SIMBAD_RESULTS) {
            System.out.println("        Simbad response:");
            for (int i = 0; i < fields.size(); i++) {
                System.out.println("            [ " + fields.get(i) + " ]: " + data.get(0).getTDSet().getContent(i));
            }
        }
    }

    private void assignFieldInfoIfRequired(SavotField field) {

        if (field.getId().equals(SimbadFields.RA_d.name())
                || field.getName().equals(SimbadFields.ra.name())) {
            raUnit = " [ " + field.getUnit() + " ]";
        }

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

        if (field.getId().equals(SimbadFields.PM_pmra.name())
                || field.getName().equals(SimbadFields.pmra.name())) {
            pmraUnit = " [ " + field.getUnit() + " ]";
        }

        if (field.getId().equals(SimbadFields.PM_pmde.name())
                || field.getName().equals(SimbadFields.pmdec.name()) ) {
            pmdecUnit = " [ " + field.getUnit() + " ]";
        }

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
        return fields.isEmpty() || data.isEmpty();
    }
}
