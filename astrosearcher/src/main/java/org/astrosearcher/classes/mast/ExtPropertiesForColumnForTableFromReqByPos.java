package org.astrosearcher.classes.mast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ExtPropertiesForColumnForTableFromReqByPos {

    @JsonProperty("cc.order")
    private int order;

    @JsonProperty("cc.width")
    private int width;

    @JsonProperty("cc.floor")
    private int floor;

    @JsonProperty("cc.defaultFacet")
    private boolean defaultFacet;

    @JsonProperty("cc.visible")
    private boolean visible;

    @JsonProperty("cc.isDate")
    private boolean isDate;

    @JsonProperty("cc.isMjd")
    private boolean isMjd;

    @JsonProperty("cc.hidden")
    private boolean hidden;

    @JsonProperty("cc.sort")
    private String sort;

    @JsonProperty("vot.name")
    private String name;

    @JsonProperty("cc.example")
    private String example;

    @JsonProperty("vot.datatype")
    private String datatype;

    @JsonProperty("vot.ID")
    private String ID;

    @JsonProperty("vot.arraysize")
    private String arraysize;

    @JsonProperty("vot.ucd")
    private String ucd;

    @JsonProperty("cc.autoFacetRule")
    private String autoFacetRule;

    @JsonProperty("vot.utype")
    private String utype;

    @JsonProperty("vot.description")
    private String description;

    @JsonProperty("cc.text")
    private String text;

    @JsonProperty("cc.separatorType")
    private String separatorType;

    @JsonProperty("cc.separator")
    private String separator;

    @JsonProperty("cc.unit")
    private String unit;

    public ExtPropertiesForColumnForTableFromReqByPos(int order, int width, int floor, boolean defaultFacet,
                                                      boolean visible, boolean isDate, boolean isMjd, boolean hidden,
                                                      String sort, String name, String example, String datatype,
                                                      String ID, String arraysize, String ucd, String autoFacetRule,
                                                      String utype, String description, String text,
                                                      String separatorType, String separator, String unit) {
        this.order = order;
        this.width = width;
        this.floor = floor;
        this.defaultFacet = defaultFacet;
        this.visible = visible;
        this.isDate = isDate;
        this.isMjd = isMjd;
        this.hidden = hidden;
        this.sort = sort;
        this.name = name;
        this.example = example;
        this.datatype = datatype;
        this.ID = ID;
        this.arraysize = arraysize;
        this.ucd = ucd;
        this.autoFacetRule = autoFacetRule;
        this.utype = utype;
        this.description = description;
        this.text = text;
        this.separatorType = separatorType;
        this.separator = separator;
        this.unit = unit;
    }
}
