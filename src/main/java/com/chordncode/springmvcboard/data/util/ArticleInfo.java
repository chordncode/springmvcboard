package com.chordncode.springmvcboard.data.util;

import java.util.List;

import lombok.Data;

@Data
public class ArticleInfo <T> {
    
    private long currentPage;
    private long totalRows;
    private long totalPages;

    private long startRow;
    private long endRow;

    private long startPage;
    private long endPage;

    private List<T> contentList;

    public ArticleInfo(long currentPage, long totalRows, long rowSize, long pageSize){

        this.currentPage = currentPage;
        this.totalRows = totalRows;

        this.startRow = (currentPage - 1) * rowSize + 1;
        this.endRow = startRow + rowSize - 1;
        if(endRow > totalRows) endRow = totalRows;

        this.totalPages = (long) Math.ceil((double) totalRows / rowSize);
        this.startPage = (currentPage - 1) / pageSize * pageSize + 1;
        this.endPage = startPage + pageSize - 1;
        if(endPage > totalPages) endPage = totalPages;

    }

}
