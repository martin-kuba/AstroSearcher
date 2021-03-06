package org.astrosearcher.classes.mast.services.caom.cone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.astrosearcher.classes.mast.TableFromReqByPos;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseForReqByPos {
    private String name;

    @SerializedName(value = "tables", alternate = "Tables")
    private List<TableFromReqByPos> tables = new ArrayList<>();

    public ResponseForReqByPos(String name, List<TableFromReqByPos> tables) {
        this.name = name;
        this.tables = tables;
    }
}
