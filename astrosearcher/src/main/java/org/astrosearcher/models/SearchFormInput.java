package org.astrosearcher.models;

import lombok.*;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.classes.constants.messages.ValidationMSG;
import org.astrosearcher.classes.constants.cds.VizierConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchFormInput {

    private String searchBy;
    private String searchInput;

//    private String SimbadFormat = SimbadConstants.DEFAULT_FORMAT;

    private double radius = Limits.DEFAULT_RADIUS;

    @Min(value = Limits.PAGE_MIN, message = ValidationMSG.PAGE_MIN_VALIDATION_MSG)
    private int    page = Limits.DEFAULT_PAGE;

    @Min(value = Limits.PAGESIZE_MIN, message = ValidationMSG.PAGESIZE_MIN_VALIDATION_MSG)
    private int    pagesize = Limits.DEFAULT_PAGESIZE;

    private String vizierCatalogueSearchBy;

    private String vizierCat = VizierConstants.DEFAULT_CATALOG;

    private MultipartFile file;

    private boolean queryMast = true;
    private boolean queryVizier = true;
    private boolean querySimbad = true;


    public String getVizierCat() {
        return vizierCat == null ? "" : vizierCat;
    }

    public double getRadiusInArcseconds() {
        return radius * Limits.DEG_TO_ARCSEC;
    }
}
