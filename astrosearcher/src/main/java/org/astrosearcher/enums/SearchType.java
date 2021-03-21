package org.astrosearcher.enums;

/**
 * This enum class represents general search options to choose from while using web application (used in dropdown menu).
 *
 * @author Ľuboslav Halama
 */
public enum SearchType {
    ID_NAME("id/name"),
    POSITION("position"),
    POSITION_CROSSMATCH("file-position");

    private String name;

    SearchType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(String type) {
        return this.name.equals(type);
    }

}
