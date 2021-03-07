package org.astrosearcher.classes.mast.services.caom.cone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseForReqByPos {
    private String           status;
    private String           msg;
    private List<JsonObject> data   = new ArrayList<>();
    private List<JsonObject> fields = new ArrayList<>();
    private JsonObject       paging;

    public ResponseForReqByPos(String status, String msg,
                               List<JsonObject> data,
                               List<JsonObject> fields,
                               JsonObject paging) {
        this.status = status;
        this.msg = msg;
        this.data.addAll(data);
        this.fields.addAll(fields);
        this.paging = paging;
    }
}
