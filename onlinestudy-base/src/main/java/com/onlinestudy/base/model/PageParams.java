package com.onlinestudy.base.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageParams {
    public static final long DEFAULT_PAGE_CURRENT = 1L;

    public static final long DEFAULT_PAGE_SIZE = 10L;

    private Long pageNo = DEFAULT_PAGE_CURRENT;

    private Long pageSize = DEFAULT_PAGE_SIZE;

    public PageParams(){

    }

    public PageParams(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
