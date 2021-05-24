package org.astrosearcher.classes.mast.services.caom.cone;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents response from Mast server for query by position.
 *
 * Class provides all necessary properties for parsing a JSON object
 * which is returned by MAST server as a result of sending of request
 * by this web application.
 *
 * @author Ľuboslav Halama
 */
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
