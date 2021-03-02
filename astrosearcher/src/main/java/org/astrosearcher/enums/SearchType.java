package org.astrosearcher.enums;

public enum SearchType {
    ID_NAME("id/name"),
    POSITION("position");

    private String name;

    SearchType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
