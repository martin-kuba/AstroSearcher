package org.astrosearcher.enums.vizier;

public enum VizierArgType {
    SOURCE("-source="),
    POSITION("-c="),
    RADIUS("-c.radius="),
    OBJECT_ID("-objID=");

    private String urlFormat;

    VizierArgType(String urlFormat) {
        this.urlFormat = urlFormat;
    }

    @Override
    public String toString() {
        return urlFormat;
    }
}
