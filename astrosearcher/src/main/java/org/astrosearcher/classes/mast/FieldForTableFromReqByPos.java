package org.astrosearcher.classes.mast;

import lombok.Getter;

@Getter
public class FieldForTableFromReqByPos {
    private final String name;
    private final String type;

    public FieldForTableFromReqByPos(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
