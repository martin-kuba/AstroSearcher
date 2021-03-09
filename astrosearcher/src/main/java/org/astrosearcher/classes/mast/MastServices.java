package org.astrosearcher.classes.mast;

public enum MastServices {
    MAST_CAOM_CONE("Mast.Caom.Cone"),
    MAST_NAME_LOOKUP("Mast.Name.Lookup");

    String name;

    MastServices(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
