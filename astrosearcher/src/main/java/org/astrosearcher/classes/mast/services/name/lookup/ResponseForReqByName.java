package org.astrosearcher.classes.mast.services.caom.cone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.astrosearcher.classes.mast.TableFromReqByPos;

import java.util.ArrayList;
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
