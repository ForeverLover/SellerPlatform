package com.templar.sellerplatform.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.templar.sellerplatform.entity.MenuTab;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/19 10:33
 * 描述：${todo}
 */
public class ModifyMenuFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private FragmentManager fm;
    private List<MenuTab> menuTabList;

    public ModifyMenuFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList,List<MenuTab> menuTabList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.fm = fm;
        this.menuTabList=menuTabList;
    }

    @Override
    public Fragment getItem(int idx) {
        return fragmentList.get(idx % fragmentList.size());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menuTabList.get(position).getName();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;  //没有找到child要求重新加载
    }


}

