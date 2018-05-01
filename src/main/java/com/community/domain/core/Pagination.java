package com.community.domain.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */
public class Pagination implements Serializable {
    // 当前页
    private int pageNumber = 1;
    // 总页数
    private int totalPage;
    // 总记录数
    private int totalRecord;
    // 页大小
    private int pageSize = 10;
    // 起始记录条数
    private int startRecord = -1;
    // 结束记录条数
    private int endRecord = -1;

    public Pagination() {
    }

    public Pagination(int pageNumber, int pageSize) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
    }

    public Pagination(int pageNumber, int pageSize, int totalRecord) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("pageNumber must be greater than 1");
        }
        this.pageNumber = pageNumber;
        this.startRecord = -1;
        this.endRecord = -1;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        if (totalPage < 0) {
            throw new IllegalArgumentException("totalPage must be greater than or equal 0");
        }
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        if (totalRecord < 0) {
            throw new IllegalArgumentException("totalRecord must be greater than or equal 0");
        }
        this.totalRecord = totalRecord;
        totalPage = totalRecord / pageSize;
        if (totalRecord % pageSize > 0) {
            totalPage += 1;
        }
        if (totalPage == 0) {
            pageNumber = 1;
            this.startRecord = -1;
            this.endRecord = -1;
        } else if (pageNumber > totalPage) {
            pageNumber = totalPage;
            this.startRecord = -1;
            this.endRecord = -1;
        }
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must be greater than  0");
        }
        this.pageSize = pageSize;
    }

    public int getStartRecord() {
        if (this.startRecord < 0) {
            this.startRecord = (pageNumber - 1) * pageSize + 1;
        }
        return this.startRecord;
    }

    public int getEndRecord() {
        if (this.endRecord < 0) {
            int fullPageRecord = this.getStartRecord() + this.getPageSize() - 1;
            this.endRecord = this.getTotalRecord() < fullPageRecord ? this.getTotalRecord() : fullPageRecord;
        }
        return this.endRecord;
    }

    public boolean isFirstPage() {
        return this.getPageNumber() == 1;
    }

    public boolean isLastPage() {
        return this.getPageNumber() >= this.getTotalPage();
    }

    public int getNextPageNumber() {
        return this.getPageNumber() + 1;
    }

    public int getPreviousPageNumber() {
        return this.getPageNumber() - 1;
    }

    public List<Integer> getLinkPageNumbers() {
        int avg = this.getPageSize() / 2;
        int startPageNumber = this.getPageNumber() - avg;
        if (startPageNumber <= 0) {
            startPageNumber = 1;
        }

        int endPageNumber = startPageNumber + this.getPageSize() - 1;
        if (endPageNumber > this.getTotalPage()) {
            endPageNumber = this.getTotalPage();
        }

        if (endPageNumber - startPageNumber < this.getPageSize()) {
            startPageNumber = endPageNumber - this.getPageSize();
            if (startPageNumber <= 0) {
                startPageNumber = 1;
            }
        }

        ArrayList result = new ArrayList();

        for (int i = startPageNumber; i <= endPageNumber; ++i) {
            result.add(new Integer(i));
        }

        return result;
    }


}