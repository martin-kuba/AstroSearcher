package org.astrosearcher.models;

import lombok.*;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.classes.constants.messages.ValidationMSG;
import org.astrosearcher.classes.constants.cds.VizierConstants;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;

/**
 * Class represents all the obtainable data from search form in the application
 * Graphic User Interface (GUI) - in HTML template.
 *
 * @author Ľuboslav Halama
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchFormInput {

    private String searchBy;

    @Size(max = Limits.INPUT_MAX_LENGTH, message = ValidationMSG.INPUT_MAX_LENGTH_VALIDATION_MSG)
    private String searchInput;

    private String SimbadFormat = SimbadConstants.DEFAULT_FORMAT;

    @NotNull(message = ValidationMSG.RADIUS_NULL_VALIDATION_MSG)
    @DecimalMin(value = Limits.RADIUS_MIN_STRING, message = ValidationMSG.RADIUS_MIN_VALIDATION_MSG)
    @DecimalMax(value = Limits.RADIUS_MAX_STRING, message = ValidationMSG.RADIUS_MAX_VALIDATION_MSG)
    private double radius = Limits.DEFAULT_RADIUS;

    @Min(value = Limits.PAGE_MIN, message = ValidationMSG.PAGE_MIN_VALIDATION_MSG)
    @Max(value = Limits.PAGE_MAX, message = ValidationMSG.PAGE_MAX_VALIDATION_MSG)
    private int    page = Limits.DEFAULT_PAGE;

    @Min(value = Limits.PAGESIZE_MIN, message = ValidationMSG.PAGESIZE_MIN_VALIDATION_MSG)
    @Max(value = Limits.PAGESIZE_MAX, message = ValidationMSG.PAGESIZE_MAX_VALIDATION_MSG)
    private int    pagesize = Limits.DEFAULT_PAGESIZE;

    private String vizierCatalogueSearchBy;

    @Size(max = Limits.VIZIER_INPUT_MAX_LENGTH, message = ValidationMSG.VIZIER_INPUT_MAX_LENGTH_VALIDATION_MSG)
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
