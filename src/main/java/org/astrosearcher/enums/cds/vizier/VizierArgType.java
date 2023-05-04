package org.astrosearcher.enums.cds.vizier;

/**
 * Subset of all the possible arguments to query Vizier service.
 *
 * @author Ľuboslav Halama
 */
public enum VizierArgType {
    META("-meta="),
    OBJECT_ID("-objID="),
    OUTPUT_LIMIT("-out.max="),
    TARGET("-c="),
    RADIUS("-c.r="),
    RADIUS_SECONDS("-c.rs="),
    RADIUS_UNIT("-c.unit="),
    SOURCE("-source="),
    WORDS("-words=");

    private String urlFormat;

    VizierArgType(String urlFormat) {
        this.urlFormat = urlFormat;
    }

    @Override
    public String toString() {
        return urlFormat;
    }
}
