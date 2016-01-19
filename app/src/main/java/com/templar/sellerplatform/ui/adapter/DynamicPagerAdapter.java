package com.templar.sellerplatform.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.templar.sellerplatform.ui.fragment.DealFragment;
import com.templar.sellerplatform.ui.fragment.EndFragment;
import com.templar.sellerplatform.ui.fragment.NoticeFragment;
import com.templar.sellerplatform.ui.fragment.OrderListFragment;
import com.templar.sellerplatform.ui.fragment.WaitFragment;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 19:18
 * 描述：$TODO
 */
public class DynamicPagerAdapter extends FragmentPagerAdapter {

    public DynamicPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle args = null;
        switch (position) {
           /* case 0:
                fragment = new NoticeFragment();
                break;
            case 1:
                fragment = new DealFragment();
                break;
            case 2:
                fragment = new WaitFragment();
                break;
            case 3:
                fragment = new EndFragment();
                break;*/
            case 0:
                args=new Bundle();
                args.putSerializable("type", 0);
                fragment = new OrderListFragment();
                fragment.setArguments(args);
                break;
            case 1:
                args=new Bundle();
                args.putSerializable("type", 1);
                fragment = new OrderListFragment();
                fragment.setArguments(args);
                break;
            case 2:
                args=new Bundle();
                args.putSerializable("type", 2);
                fragment = new OrderListFragment();
                fragment.setArguments(args);
                break;
            case 3:
                args=new Bundle();
                args.putSerializable("type",3);
                fragment = new OrderListFragment();
                fragment.setArguments(args);
                break;
        }
        return fragment;
    }

    public int getCount() {
        return 4;
    }
}
