package org.astrosearcher.classes.mast;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseForReqByPos {
    private String name;

    @SerializedName(value = "tables", alternate = "Tables")
    private List<TableFromReqByPos> tables = new ArrayList<>();

//    @SerializedName(value = "tables", alternate = "Tables")
//    private JsonArray tables = new JsonArray();

    public ResponseForReqByPos(String name, List<TableFromReqByPos> tables) {
        this.name = name;
        this.tables.addAll(tables);
    }

//    public ResponseForReqByPos(String name, JsonArray tables) {
//        this.name = name;
//        this.tables.addAll(tables);
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public List<TableFromReqByPos> getTables() {
        return tables;
    }

//    public JsonArray getTables() {
//        return tables;
//    }

//    public void setTables(List<TableFromReqByPos> tables) {
//        this.tables = tables;
//    }
}
