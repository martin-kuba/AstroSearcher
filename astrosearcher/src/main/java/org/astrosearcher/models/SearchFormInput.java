package org.astrosearcher.models;

import lombok.*;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.ValidationMSG;
import org.astrosearcher.classes.constants.VizierConstants;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchFormInput {

    private String searchBy;
    private String searchInput;

    @Min(value = Limits.PAGE_MIN, message = ValidationMSG.PAGE_MIN_VALIDATION_MSG)
    private int    page = Limits.DEFAULT_PAGE;

    @Min(value = Limits.PAGESIZE_MIN, message = ValidationMSG.PAGESIZE_MIN_VALIDATION_MSG)
    private int    pagesize = Limits.DEFAULT_PAGESIZE;

    @NotNull(message = "Vizier catalogue field cannot be empty!")
    @NotBlank(message = "Vizier catalogue field cannot be empty!")
    private String vizierCat = VizierConstants.DEFAULT_CATALOG;

//    private String filename;
    private MultipartFile file;

}
