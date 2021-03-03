package org.astrosearcher.classes.mast;

import lombok.Getter;

@Getter
public class FieldForTableFromReqByPos {
    private final String name;
//    private String label;
    private final String type;

    public FieldForTableFromReqByPos(String name, String type) {
        this.name = name;
//        this.label = name; // in case there would not be stated label for given field in response
        this.type = type;
    }

//    public void setLabel(String label) {
//        this.label = label;
//    }
}
