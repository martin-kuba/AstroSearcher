package org.astrosearcher.enums.mast;

import org.astrosearcher.enums.SearchType;

/**
 * This enum class represents subset of services ("queries by") provided by MAST server and listed in:
 * ->   https://mast.stsci.edu/api/v0/_services.html   <-
 *
 * @author Ľuboslav Halama
 */
public enum MastServices {
    MAST_CAOM_CONE("Mast.Caom.Cone", SearchType.POSITION),
    MAST_CAOM_CROSSMATCH("Mast.Caom.Crossmatch", SearchType.POSITION_CROSSMATCH),
    MAST_NAME_LOOKUP("Mast.Name.Lookup", SearchType.ID_NAME);

    private String name;
    private SearchType usedFor;

    MastServices(String name, SearchType usedFor) {
        this.name    = name;
        this.usedFor = usedFor;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(SearchType searchType) {
        return this.usedFor.equals(searchType);
    }
}
