package com.templar.sellerplatform.entity;

import java.io.Serializable;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 18:09
 * 描述：${todo}
 */
public class MenuTab implements Serializable{
    private String id;
    private String name;
    private int order;
    private String weburl;
    private String hweburl;

    public MenuTab(String id, String name, int order, String weburl, String hweburl) {
        super();
        this.id = id;
        this.name = name;
        this.order = order;
        this.weburl = weburl;
        this.hweburl = hweburl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getHweburl() {
        return hweburl;
    }

    public void setHweburl(String hweburl) {
        this.hweburl = hweburl;
    }
}
