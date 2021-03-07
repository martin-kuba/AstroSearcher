package org.astrosearcher.classes.mast.services.caom.cone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseForReqByPos {
    private String           status;
    private String           msg;
    private List<JsonObject> data;
    private List<JsonObject> fields;
    private JsonObject       paging;

    public ResponseForReqByPos(String status, String msg,
                               List<JsonObject> data,
                               List<JsonObject> fields,
                               JsonObject paging) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.fields = fields;
        this.paging = paging;
    }
}
