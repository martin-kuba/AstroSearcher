package org.astrosearcher.classes.simbad;

public enum SimbadServices {
    SIMBAD_ID("sim-id"),
    SIMBAD_COORDINATES("sim-coo");

    String name;

    SimbadServices(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
