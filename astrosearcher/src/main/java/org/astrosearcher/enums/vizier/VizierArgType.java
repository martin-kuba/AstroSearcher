package org.astrosearcher.enums.vizier;

public enum VizierArgType {
    OBJECT_ID("-objID="),
    OUTPUT_LIMIT("-out.max="),
    POSITION("-c="),
    RADIUS("-c.r="),
    RADIUS_UNIT("-c.unit="),
    SOURCE("-source=");

    private String urlFormat;

    VizierArgType(String urlFormat) {
        this.urlFormat = urlFormat;
    }

    @Override
    public String toString() {
        return urlFormat;
    }
}
