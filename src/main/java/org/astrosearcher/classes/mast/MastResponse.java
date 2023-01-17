package org.astrosearcher.classes.mast;

import com.google.gson.JsonObject;
import lombok.Getter;
import org.astrosearcher.TomcatConfig;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.enums.mast.services.caom.cone.CaomFields;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents MAST response (data part).
 *
 * Class stores:
 *     1.) fields   -> column names in table
 *                  -> used with pre-defined settings
 *                     (@see org.astrosearcher.enums.mast.services.caom.cone.CaomFields)
 *
 *     2.) data     -> in format of Json Object
 *
 * @author Ľuboslav Halama
 */
@Getter
public class MastResponse {

    private static final Logger log = LoggerFactory.getLogger(MastResponse.class);

    private List<CaomFields> fields = new ArrayList<>();

    private List<JsonObject> data = new ArrayList<>();

    public MastResponse() {}

    public MastResponse(@NotNull ResponseForReqByPos response) {
        this(response.getFields(), response.getData());
    }

    public MastResponse(List<JsonObject> responseFields, List<JsonObject> data) {

        if (responseFields == null) {
            return;
        }

        for (CaomFields caomField : CaomFields.values()) {
            for (JsonObject field : responseFields) {
                try {
                    if ( !caomField.equals(field.get("name").getAsString()) ) {
                        continue;
                    }

                    if ( caomField.isVisible() ) {
                        this.fields.add(caomField);
                    }
                    break;
                } catch (NullPointerException npe) {
                    log.error("MAST response exception: {}", ExceptionMSG.INVALID_MAST_FIELD_NAME_EXCEPTION, npe);
                    throw new NullPointerException(
                            ExceptionMSG.INVALID_MAST_FIELD_NAME_EXCEPTION + npe
                    );
                } catch (IllegalArgumentException iae) {
                    log.error("MAST response exception: {}", ExceptionMSG.MAST_FIELD_NAME_NOT_MATCHED_EXCEPTION, iae);
                    throw new IllegalArgumentException(
                            ExceptionMSG.MAST_FIELD_NAME_NOT_MATCHED_EXCEPTION + iae
                    );
                }
            }
        }

        this.data = data;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
