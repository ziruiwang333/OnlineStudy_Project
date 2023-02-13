package com.onlinestudy.base.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PageResult<T> {
    public PageResult() {
    }

    public PageResult(List<T> items, long counts, long page, long pageSize) {
        this.items = items;
        this.counts = counts;
        this.page = page;
        this.pageSize = pageSize;
    }

    private List<T> items;

    private long counts;

    private long page;

    private long pageSize;
}
