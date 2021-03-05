package org.astrosearcher.classes.mast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.astrosearcher.classes.mast.caom.cone.CaomFields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
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
    private List<JsonArray> rows = new ArrayList<>();

    private Map<CaomFields, Integer> responseDataMapper;

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
                             List<JsonArray> rows) {

        this.name = name;
        this.extendedProperties = extendedProperties;
        this.columns.addAll(columns);
        this.fields.addAll(fields);
        this.rows.addAll(rows);
    }

    public void initMapper() {
        responseDataMapper = new HashMap<>();

        System.out.println("Current state of mapper: " + responseDataMapper);
        System.out.println("values: " + responseDataMapper.values());

        for (CaomFields caomField : CaomFields.values()) {
            System.out.print("Field: " + caomField);
            responseDataMapper.put(caomField, -1);
            for (int index = 0; index < fields.size(); index++) {
                if (caomField.equals(fields.get(index).getName())) {
                    responseDataMapper.put(caomField, index);
                }
            }
            System.out.println("[ " + responseDataMapper.get(caomField) + " ]");
        }
    }
}
