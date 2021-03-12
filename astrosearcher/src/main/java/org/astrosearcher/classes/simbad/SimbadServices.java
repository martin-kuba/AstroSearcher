package org.astrosearcher.classes.simbad;

/**
 * This enum class represents subset of services ("queries by") provided by Simbad server.
 *
 * @author Ľuboslav Halama
 */
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
