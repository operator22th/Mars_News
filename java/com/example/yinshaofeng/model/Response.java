package com.example.yinshaofeng.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{
    @SerializedName("pageSize")
    private int pageSize;

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<ArticlesItem> data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ArticlesItem> getArticles() {
        return data;
    }

    public void setArticles(List<ArticlesItem> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}