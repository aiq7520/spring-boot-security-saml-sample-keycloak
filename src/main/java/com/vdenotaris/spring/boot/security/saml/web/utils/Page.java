package com.vdenotaris.spring.boot.security.saml.web.utils;


import java.util.List;
/**
 * @Classname Page
 * @Description TODO
 * @Date 2022/4/18 14:10
 * @Created by ge.ji
 */
public class Page<T> {

    private Integer pageIndex;
    private Integer pageCount;
    private List<T> items;
    private Integer pageTotal;
    private Integer itemTotal;
    private Integer firstIndex;//查询的起始记录

    public Page() {
    }

    public Page(Integer pageIndex, Integer pageCount, List<T> items, Integer itemTotal) {
        this.pageIndex = pageIndex;
        this.pageCount = pageCount;
        this.items = items;
        this.itemTotal = itemTotal;
        this.pageTotal = (this.itemTotal + this.pageCount - 1) / this.pageCount;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Integer itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Integer getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(Integer firstIndex) {
        this.firstIndex = firstIndex;
    }
}