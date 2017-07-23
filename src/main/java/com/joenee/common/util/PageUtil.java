package com.joenee.common.util;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * PageUtil
 *
 * @author Li zheng
 * @description
 * @date 2016/4/21
 */
public class PageUtil<T> implements Serializable {
    //页面大小
    private int limit;
    //页码
    private int offset;
    //页码
    private int pageNumber;
    //总条数
    private int total;
    //数据
    private List rows;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
