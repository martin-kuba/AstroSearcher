package org.astrosearcher.classes.mast;

public class Paging {
    private int page;
    private int pageSize;
    private int pagesFiltered;
    private int rows;
    private int rowsFiltered;
    private int rowsTotal;

    public Paging(int page, int pageSize, int pagesFiltered, int rows, int rowsFiltered, int rowsTotal) {
        this.page          = page;
        this.pageSize      = pageSize;
        this.pagesFiltered = pagesFiltered;
        this.rows          = rows;
        this.rowsFiltered  = rowsFiltered;
        this.rowsTotal     = rowsTotal;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPagesFiltered() {
        return pagesFiltered;
    }

    public void setPagesFiltered(int pagesFiltered) {
        this.pagesFiltered = pagesFiltered;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRowsFiltered() {
        return rowsFiltered;
    }

    public void setRowsFiltered(int rowsFiltered) {
        this.rowsFiltered = rowsFiltered;
    }

    public int getRowsTotal() {
        return rowsTotal;
    }

    public void setRowsTotal(int rowsTotal) {
        this.rowsTotal = rowsTotal;
    }
}
