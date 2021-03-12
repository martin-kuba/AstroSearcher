package org.astrosearcher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchFormInput {

    private String searchBy;
    private String searchInput;

    @Min(value = 1, message = "Page number must be positive.")
    private int    page = 1;

    @Min(value = 1, message = "At least 1 result must be allowed.")
    private int    pagesize = 500;




}
