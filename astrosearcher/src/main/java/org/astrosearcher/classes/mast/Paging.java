package org.astrosearcher.classes.mast;

import lombok.Getter;

@Getter
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
}
