package org.astrosearcher.classes.mast.services.name.lookup;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.List;

/**
 * Class represents response from Mast server for query by name.
 *
 * Class provides all necessary properties for parsing a JSON object
 * which is returned by MAST server as a result of sending of request
 * by this web application.
 *
 * @author Ľuboslav Halama
 */
@Getter
public class ResponseForReqByName {
    private List<JsonObject> resolvedCoordinate;
    private String status;

    public ResponseForReqByName(List<JsonObject> resolvedCoordinate, String status) {
        this.resolvedCoordinate = resolvedCoordinate;
        this.status = status;
    }
}
