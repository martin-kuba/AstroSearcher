package org.astrosearcher.classes.simbad;

public enum SimbadArgType {

    ID("Ident"),
    COORDINATES("Coord"),
    RADIUS("Radius"),
    RADIUS_UNIT("Radius.unit");

    private String urlName;

    SimbadArgType(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return urlName;
    }
}
