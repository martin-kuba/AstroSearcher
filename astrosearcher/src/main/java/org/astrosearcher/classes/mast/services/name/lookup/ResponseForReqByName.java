package org.astrosearcher.classes.mast.services.name.lookup;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseForReqByName {
    private List<JsonObject> resolvedCoordinate;
    private String status;

    public ResponseForReqByName(List<JsonObject> resolvedCoordinate, String status) {
        this.resolvedCoordinate = resolvedCoordinate;
        this.status = status;
    }
}
