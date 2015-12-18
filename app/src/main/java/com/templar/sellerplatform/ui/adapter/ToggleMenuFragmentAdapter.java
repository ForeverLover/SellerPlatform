package com.templar.sellerplatform.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 18:05
 * 描述：${todo}
 */
public class ToggleMenuFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private FragmentManager fm;

    public ToggleMenuFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int idx) {
        return fragmentList.get(idx % fragmentList.size());
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
