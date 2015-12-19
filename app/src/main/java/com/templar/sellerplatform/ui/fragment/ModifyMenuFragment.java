package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.MenuTab;
import com.templar.sellerplatform.parser.MenuParser;
import com.templar.sellerplatform.ui.adapter.ModifyMenuFragmentAdapter;
import com.templar.sellerplatform.widget.CustomHorizotalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 17:12
 * 描述：${todo}
 */
public class ModifyMenuFragment extends BaseFragment {
    @ViewInject(R.id.modify_scroll_layout)
    private CustomHorizotalScrollView modify_scroll_layout;
    @ViewInject(R.id.modify_veiewpager)
    private ViewPager modify_veiewpager;

    private ModifyMenuFragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;
    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_modify_menu;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {}

    @Override
    public void initData() {
        super.initData();
        List<MenuTab> menuTabList = MenuParser.getInstance().parseMenuTabList();
        if (menuTabList != null && !menuTabList.isEmpty()) {
            fragmentList = new ArrayList<Fragment>();
            for (int i = 0; i < menuTabList.size(); i++) {
                ToggleMenuSubFragment fragment = new ToggleMenuSubFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cname", menuTabList.get(i).getName());
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }
            fragmentAdapter = new ModifyMenuFragmentAdapter(super.getActivity().getSupportFragmentManager(), fragmentList,menuTabList);
            modify_veiewpager.setAdapter(fragmentAdapter);
//            modify_veiewpager.setOffscreenPageLimit(2);
//            modify_veiewpager.setCurrentItem(0);
//            modify_veiewpager.addOnPageChangeListener(this);
            modify_scroll_layout.setViewPager(modify_veiewpager);
        }
    }
}
