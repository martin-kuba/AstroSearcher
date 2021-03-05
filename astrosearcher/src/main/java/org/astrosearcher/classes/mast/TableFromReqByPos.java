package org.astrosearcher.classes.mast;

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

    @SerializedName(value = "columns", alternate = "Columns")
    private List<ColumnForTableFromReqByPos> columns = new ArrayList<>();

    @SerializedName(value = "fields", alternate = "Fields")
    private List<FieldForTableFromReqByPos> fields = new ArrayList<>();

    @SerializedName(value = "rows", alternate = "Rows")
    private List<JsonArray> rows = new ArrayList<>();

    private Map<CaomFields, Integer> responseDataMapper;

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

        for (CaomFields caomField : CaomFields.values()) {
            responseDataMapper.put(caomField, -1);
            for (int index = 0; index < fields.size(); index++) {
                if (caomField.equals(fields.get(index).getName())) {
                    responseDataMapper.put(caomField, index);
                }
            }
        }
    }
}
