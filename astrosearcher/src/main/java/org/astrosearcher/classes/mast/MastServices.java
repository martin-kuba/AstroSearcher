package org.astrosearcher.classes.mast;

/**
 * This enum class represents subset of services ("queries by") provided by MAST server and listed in:
 * ->   https://mast.stsci.edu/api/v0/_services.html   <-
 *
 * @author Ľuboslav Halama
 */
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
