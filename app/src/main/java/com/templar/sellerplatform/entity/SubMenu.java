package com.templar.sellerplatform.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/21 11:19
 * 描述：${todo}
 */
public class SubMenu implements Serializable{
    private String id;
    private String name;
    private boolean on;
    private boolean isAdd;
    private List<MenuItem> menuItemList;
    private List<SubMenu> necessaryViceList;
    private List<SubMenu> unnecessaryViceList;
    /**
     * 0-至多
     * 1-至少
     */
    private int selectionType;
    /**
     * 选择数量
     */
    private int selectCount;



    /**
     * 0-必选项
     * 1-非必选项
     */
    private int type;

    public int getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(int selectionType) {
        this.selectionType = selectionType;
    }

    public int getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(int selectCount) {
        this.selectCount = selectCount;
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

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public List<SubMenu> getNecessaryViceList() {
        return necessaryViceList;
    }

    public void setNecessaryViceList(List<SubMenu> necessaryViceList) {
        this.necessaryViceList = necessaryViceList;
    }

    public List<SubMenu> getUnnecessaryViceList() {
        return unnecessaryViceList;
    }

    public void setUnnecessaryViceList(List<SubMenu> unnecessaryViceList) {
        this.unnecessaryViceList = unnecessaryViceList;
    }
}
