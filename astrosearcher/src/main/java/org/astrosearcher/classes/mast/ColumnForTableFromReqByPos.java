package org.astrosearcher.classes.mast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class ColumnForTableFromReqByPos {

    private String text;
    private String dataIndex;
    private String ignoreValue;

    @SerializedName(value = "extendedProperties", alternate = "ExtendedProperties")
    private JsonObject extendedProperties;

    public ColumnForTableFromReqByPos(String text, String dataIndex, String ignoreValue, JsonObject extendedProperties) {
        this.text = text;
        this.dataIndex = dataIndex;
        this.ignoreValue = ignoreValue;
        this.extendedProperties = extendedProperties;
    }

    public String getLabel() {
        Gson gson = new Gson();
        String label = gson.fromJson(extendedProperties.get("cc.text"), String.class);

        if (label == null) {
            return null;
        }
        return label;
    }

    public boolean isString() {
        if (extendedProperties.get("vot.datatype") == null) {
            return false;
        }
        return extendedProperties.get("vot.datatype").getAsString().equals("char");
    }

    public boolean isHidden() {
        if (getLabel() == null) {
            return true;
        }

        JsonElement var1 = extendedProperties.get("cc.hidden");
        if (var1 != null && !var1.getAsBoolean()) {
            return false;
        }

        JsonElement var2 = extendedProperties.get("cc.visible");
        if (var2 != null && var2.getAsBoolean()) {
            return false;
        }

        if (var1 == null && var2 == null) {
            return false;
        }

        return true;
    }
}
