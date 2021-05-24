package org.astrosearcher.validators;

import org.astrosearcher.classes.constants.RegularExpressions;
import org.astrosearcher.classes.constants.messages.ValidationMSG;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class serves for a validation of a file that is obtained from search form
 * in the application Graphic User Interface (GUI) - in HTML template.
 *
 * @author Ľuboslav Halama
 */
@Component
public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MultipartFile.class.isAssignableFrom(aClass);
    }

    public void validate(SearchFormInput input, Errors errors) {
        if ( !input.getSearchBy().equals(SearchType.POSITION_CROSSMATCH.toString()) ) {
            return;
        }
        validate(input.getFile(), errors);
    }

    @Override
    public void validate(Object o, Errors errors) {

        MultipartFile file = (MultipartFile) o;

        if (file.getSize() == 0) {
            errors.reject("file", ValidationMSG.FILE_EMPTY_VALIDATION_MSG);
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            String line = reader.readLine();
            if (line == null) {
                errors.reject("file", ValidationMSG.FILE_EMPTY_VALIDATION_MSG);
                return;
            }

            // if file does not include descriptive first line
            if ( !line.matches(RegularExpressions.FILE_STRICT_COLUMN_NAMES_LINE)) {
                errors.reject("file", ValidationMSG.FILE_INVALID_FIRST_ROW_VALIDATION_MSG);
            }

            // read data
            while ( (line = reader.readLine()) != null ) {
                if ( !line.matches(RegularExpressions.FILE_COORDINATES_LINE)) {
                    errors.reject("file", ValidationMSG.FILE_INVALID_COORDINATES_LINE);
                }
            }

        } catch (IOException e) {
            errors.reject("file", ValidationMSG.FILE_FAILED_TO_OPEN);
        }
    }
}
