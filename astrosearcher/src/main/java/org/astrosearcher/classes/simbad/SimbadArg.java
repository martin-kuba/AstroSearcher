package org.astrosearcher.classes.simbad;

import org.astrosearcher.classes.PositionInput;

public class SimbadArg {

    private SimbadArgType type;
    private Object value;

    public SimbadArg(SimbadArgType type, Object value) {
        this.type  = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "&" + type.toString() + "=" + value;
    }
}
