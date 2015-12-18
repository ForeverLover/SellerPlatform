package com.templar.sellerplatform.parser;

import com.templar.sellerplatform.entity.MenuTab;

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

        menuTabList=new ArrayList<MenuTab>();
        menuTabList.add(new MenuTab("T1348647909107","头条",0,
                "http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html",""));
        menuTabList.add(new MenuTab("T1348648517839","娱乐",0,
                "http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html",""));
        menuTabList.add(new MenuTab("T1348649079062","体育",0,
                "http://c.3g.163.com/nc/article/list/T1348649079062/0-20.html",""));
        menuTabList.add(new MenuTab("T1348648756099","财经",0,"http://c.3g.163.com/nc/article/list/T1348648756099/0-20.html",""));

        menuTabList.add(new MenuTab("","热点",0,
                "http://c.3g.163.com/recommend/getSubDocPic?passport=&devId=000000000000000&size=20",""));
        menuTabList.add(new MenuTab("","科技",0,"",""));
        menuTabList.add(new MenuTab("","图片",0,"",""));
        menuTabList.add(new MenuTab("","汽车",0,"",""));
        menuTabList.add(new MenuTab("","时尚",0,"",""));
        menuTabList.add(new MenuTab("","成都",0,"",""));
        menuTabList.add(new MenuTab("","科技",0,"",""));
        menuTabList.add(new MenuTab("","跟帖",0,"",""));
        menuTabList.add(new MenuTab("","直播",0,"",""));
        menuTabList.add(new MenuTab("","轻松一刻",0,"",""));
        menuTabList.add(new MenuTab("","汽车",0,"",""));
        menuTabList.add(new MenuTab("","段子",0,"",""));
        menuTabList.add(new MenuTab("","军事",0,"",""));
        menuTabList.add(new MenuTab("","房产",0,"",""));
        menuTabList.add(new MenuTab("","历史",0,"",""));
        menuTabList.add(new MenuTab("","家居",0,"",""));
        menuTabList.add(new MenuTab("","原创",0,"",""));
        menuTabList.add(new MenuTab("","游戏",0,"",""));
        menuTabList.add(new MenuTab("","健康",0,"",""));
        menuTabList.add(new MenuTab("","政务",0,"",""));
        menuTabList.add(new MenuTab("","漫画",0,"",""));
        menuTabList.add(new MenuTab("","哒哒",0,"",""));
        menuTabList.add(new MenuTab("","彩票",0,"",""));
        return menuTabList;
    }
}
