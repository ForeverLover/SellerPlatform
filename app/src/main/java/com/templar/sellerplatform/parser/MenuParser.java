package com.templar.sellerplatform.parser;

import com.templar.sellerplatform.entity.MenuTab;
import com.templar.sellerplatform.entity.SubMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 18:10
 * 描述：${todo}
 */
public class MenuParser {
    private static MenuParser instance = null;

    public static MenuParser getInstance() {
        if (instance == null)
            instance = new MenuParser();
        return instance;
    }


    public List<MenuTab> parseMenuTabList(/*String data*/) {
       /* if (StringUtils.isEmpty(data)) return null;*/
        List<MenuTab> menuTabList = null;

        menuTabList = new ArrayList<>();
        MenuTab menuTab = new MenuTab();
        menuTab.setName("吐司");
        menuTabList.add(menuTab);

        menuTab = new MenuTab();
        menuTab.setName("飲料");
        List<SubMenu> subMenuList = new ArrayList<>();
        SubMenu subMenu = new SubMenu();
        subMenu.setName("豆漿");
        subMenu.setOn(true);
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("紅茶");
        subMenu.setOn(true);
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("烏龍茶");
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("多多綠茶");
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("錫蘭紅茶");
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("豆漿紅茶");
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("鴛鴦奶茶");
        subMenu.setOn(true);
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("珍珠紅茶");
        subMenu.setOn(true);
        subMenuList.add(subMenu);
        subMenu = new SubMenu();
        subMenu.setName("紅茶拿鐵");
        subMenuList.add(subMenu);
        menuTab.setSubMenuList(subMenuList);
        menuTabList.add(menuTab);

        menuTab = new MenuTab();
        menuTab.setName("蛋餅");
        menuTabList.add(menuTab);

        menuTab = new MenuTab();
        menuTab.setName("漢堡");
        menuTabList.add(menuTab);

        menuTab = new MenuTab();
        menuTab.setName("焗烤燴飯");
        menuTabList.add(menuTab);

        return menuTabList;
    }

    public List<SubMenu> parseViceList(/*String data*/) {
       /* if (StringUtils.isEmpty(data)) return null;*/
        List<SubMenu> menuTabList = null;

        menuTabList = new ArrayList<>();
        SubMenu submenu = new SubMenu();
        submenu.setName("冰塊");
        submenu.setOn(true);
        menuTabList.add(submenu);

        submenu = new SubMenu();
        submenu.setOn(true);
        submenu.setName("甜度");

        menuTabList.add(submenu);

        submenu = new SubMenu();
        submenu.setName("加料");
        menuTabList.add(submenu);

        submenu = new SubMenu();
        submenu.setName("麵包");
        menuTabList.add(submenu);

        submenu = new SubMenu();
        submenu.setName("套餐");
        submenu.setOn(true);
        menuTabList.add(submenu);

        submenu = new SubMenu();
        submenu.setName("小點");
        submenu.setOn(true);
        menuTabList.add(submenu);

        submenu = new SubMenu();
        submenu.setName("燙頭");
        menuTabList.add(submenu);

        return menuTabList;
    }
}
