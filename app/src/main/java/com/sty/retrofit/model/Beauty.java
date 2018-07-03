package com.sty.retrofit.model;

/**
 * Created by tian on 2018/7/3.
 */

public class Beauty {

    /**
     * createdAt : 2016-10-26T11:14:11.143Z
     * publishedAt : 2016-10-26T11:28:10.759Z
     * type : 美图
     * url : http://ww4.sinaimg.cn/large/610dc034jw1f95hzq3p4rj20u011htbm.jpg
     */

    private String createdAt;
    private String publishedAt;
    private String type;
    private String url;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
