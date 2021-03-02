package org.astrosearcher.classes.mast;

public class ExtPropertiesForTableFromReqByPos {
    private Paging paging;

    public ExtPropertiesForTableFromReqByPos(Paging paging) {
        this.paging = paging;
    }

    public int getPage() {
        return paging.getPage();
    }

    public int getPageSize() {
        return paging.getPageSize();
    }

    public int getPagesFiltered() {
        return paging.getPagesFiltered();
    }

    public int getRows() {
        return paging.getRows();
    }

    public int getRowsFiltered() {
        return paging.getRowsFiltered();
    }

    public int getRowsTotal() {
        return paging.getRowsTotal();
    }
}
