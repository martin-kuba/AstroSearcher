package org.astrosearcher.enums;

import org.astrosearcher.enums.cds.vizier.VizierArgType;

public enum VizierCatalogueSearch {
    code("code", VizierArgType.SOURCE),
    words("words", VizierArgType.WORDS);

    private String name;
    private VizierArgType arg;

    VizierCatalogueSearch(String name, VizierArgType arg) {
        this.name = name;
        this.arg = arg;
    }

    @Override
    public String toString() {
        return name;
    }

    public VizierArgType getArgType() {
        return arg;
    }
}
