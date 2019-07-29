package com.bm.jw.utils;

/**
 * 这是用于分页用的工具类
 * */
public class BaseParams {

    public Long page;
    public Long limit;

    public BaseParams() {
        this.page = 1L;
        this.limit = 10L;
    }

    public BaseParams(Long page, Long limit) {
        this.page = (null == page) ? 1L : page;
        this.limit = (null == limit) ? 10L : limit;
    }

}