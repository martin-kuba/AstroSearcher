package org.astrosearcher.classes.mast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ColumnForTableFromReqByPos {
    private String text;
    private String dataIndex;
    private String ignoreValue;

    @SerializedName(value = "extendedProperties", alternate = "ExtendedProperties")
    private JsonObject extendedProperties;
//    private ExtPropertiesForColumnForTableFromReqByPos extendedProperties;

//    public ColumnForTableFromReqByPos(String text, String dataIndex, String ignoreValue,
//                                      ExtPropertiesForColumnForTableFromReqByPos extendedProperties) {
//        this.text = text;
//        this.dataIndex = dataIndex;
//        this.ignoreValue = ignoreValue;
//        this.extendedProperties = extendedProperties;
//    }


    public ColumnForTableFromReqByPos(String text, String dataIndex, String ignoreValue, JsonObject extendedProperties) {
        this.text = text;
        this.dataIndex = dataIndex;
        this.ignoreValue = ignoreValue;
        this.extendedProperties = extendedProperties;
    }
}
