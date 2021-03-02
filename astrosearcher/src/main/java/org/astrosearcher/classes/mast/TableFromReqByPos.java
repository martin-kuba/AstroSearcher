package org.astrosearcher.classes.mast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TableFromReqByPos {
    private String name;

    @SerializedName(value = "extendedProperties", alternate = "ExtendedProperties")
    private JsonObject extendedProperties;
//    private ExtPropertiesForTableFromReqByPos extendedProperties;

    @SerializedName(value = "columns", alternate = "Columns")
    private List<ColumnForTableFromReqByPos> columns = new ArrayList<>();

    @SerializedName(value = "fields", alternate = "Fields")
    private List<FieldForTableFromReqByPos> fields = new ArrayList<>();

    @SerializedName(value = "rows", alternate = "Rows")
    private JsonArray rows = new JsonArray();

//    public TableFromReqByPos(String name, ExtPropertiesForTableFromReqByPos extendedProperties,
//                             List<ColumnForTableFromReqByPos> columns,
//                             List<FieldForTableFromReqByPos> fields,
//                             JsonArray rows) {
//        this.name = name;
//        this.extendedProperties = extendedProperties;
//        this.columns.addAll(columns);
//        this.fields.addAll(fields);
//        this.rows.addAll(rows);
//    }

    public TableFromReqByPos(String name, JsonObject extendedProperties,
                             List<ColumnForTableFromReqByPos> columns,
                             List<FieldForTableFromReqByPos> fields,
                             JsonArray rows) {
        this.name = name;
        this.extendedProperties = extendedProperties;
        this.columns.addAll(columns);
        this.fields.addAll(fields);
        this.rows.addAll(rows);
    }

    public String getName() {
        return name;
    }

//    public ExtPropertiesForTableFromReqByPos getExtendedProperties() {
//        return extendedProperties;
//    }

    public JsonObject getExtendedProperties() {
        return extendedProperties;
    }

    public List<ColumnForTableFromReqByPos> getColumns() {
        return columns;
    }

    public List<FieldForTableFromReqByPos> getFields() {
        return fields;
    }

    public JsonArray getRows() {
        return rows;
    }
}
