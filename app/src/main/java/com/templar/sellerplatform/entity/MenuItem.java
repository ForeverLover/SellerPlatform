package com.templar.sellerplatform.entity;

import java.io.Serializable;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/22 11:42
 * 描述：$TODO
 */
public class MenuItem implements Serializable{
    private String id;
    private String itemName="";
    private String itemPrice="";
    private boolean isNecessary;
    private boolean isAdd;
    private boolean isOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(boolean isNecessary) {
        this.isNecessary = isNecessary;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }
}
