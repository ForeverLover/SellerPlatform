package com.templar.sellerplatform.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.MenuTab;
import com.templar.sellerplatform.listener.MyTabActivityResultListener;
import com.templar.sellerplatform.parser.MenuParser;
import com.templar.sellerplatform.ui.adapter.ModifyMenuFragmentAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 17:12
 * 描述：${todo}
 */
public class ModifyMenuFragment extends BaseFragment implements ViewPager.OnPageChangeListener,MyTabActivityResultListener {
    @ViewInject(R.id.modify_scroll_layout)
    private HorizontalScrollView modify_scroll_layout;
    @ViewInject(R.id.modify_radio_layout)
    private RadioGroup modify_radio_layout;
    @ViewInject(R.id.modify_veiewpager)
    private ViewPager modify_veiewpager;

    private List<Fragment> fragmentList;
    private ModifyMenuFragmentAdapter menuFragmentAdapter;
    private int currentIndex;

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_modify_menu;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {
        initTab(inflater);
        initViewPager();
    }


    private void initViewPager() {
        List<MenuTab> menuTabList = MenuParser.getInstance().parseMenuTabList();
        if (menuTabList != null && !menuTabList.isEmpty()) {
            fragmentList = new ArrayList<Fragment>();
            for (int i = 0; i < menuTabList.size(); i++) {
                ModifySubMenuFragment fragment = new ModifySubMenuFragment();
                Bundle args = new Bundle();
                args.putSerializable("subMenuList", (Serializable) menuTabList.get(i).getSubMenuList());
                fragment.setArguments(args);
                fragmentList.add(fragment);
            }
            menuFragmentAdapter = new ModifyMenuFragmentAdapter(super.getActivity().getSupportFragmentManager(), fragmentList);
            modify_veiewpager.setAdapter(menuFragmentAdapter);
            modify_veiewpager.setOffscreenPageLimit(2);
            modify_veiewpager.setCurrentItem(0);
            modify_veiewpager.addOnPageChangeListener(this);
        }
    }

    private void initTab(LayoutInflater inflater) {
        List<MenuTab> channelList = MenuParser.getInstance().parseMenuTabList();
        for (int i = 0; i < channelList.size(); i++) {
            RadioButton rb = (RadioButton) inflater.
                    inflate(R.layout.tab_rb, null);
            rb.setId(i);
            rb.setText(channelList.get(i).getName());
            RadioGroup.LayoutParams params = new
                    RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.MATCH_PARENT);

            modify_radio_layout.addView(rb, params);
        }
        modify_radio_layout.check(0);
        modify_radio_layout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                modify_veiewpager.setCurrentItem(checkedId);
                currentIndex = checkedId;
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTab(position);
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTab(int idx) {
        RadioButton rb = (RadioButton) modify_radio_layout.getChildAt(idx);
        rb.setChecked(true);
        int left = rb.getLeft();
        int width = rb.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        super.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int len = left + width / 2 - screenWidth / 2;
        modify_scroll_layout.smoothScrollTo(len, 0);
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }*/

    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = fragmentList.get(currentIndex);
        if (fragment != null) {
            if (fragment instanceof MyTabActivityResultListener) {
                MyTabActivityResultListener listener = (MyTabActivityResultListener) fragment;
                listener.onTabActivityResult(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


