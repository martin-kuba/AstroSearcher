package org.astrosearcher.enums.simbad;

/**
 * This enum class represents subset of services ("queries by") provided by Simbad server.
 *
 * @author Ľuboslav Halama
 */
public enum SimbadServices {
    SIMBAD_ID("sim-id"),
    SIMBAD_COORDINATES("sim-coo"),
    SIMBAD_REFERENCE("sim-ref"),
    SIMBAD_ID_REFERENCE("sim-id-refs");

    String name;

    SimbadServices(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
