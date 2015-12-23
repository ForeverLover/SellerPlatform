package com.templar.sellerplatform.ui.fragment;

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
import com.templar.sellerplatform.parser.MenuParser;
import com.templar.sellerplatform.ui.adapter.ToggleMenuFragmentAdapter;
import com.templar.sellerplatform.utils.MLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 14:40
 * 描述：${todo}
 */
public class ToggleMenuFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @ViewInject(R.id.toggle_scroll_layout)
    private HorizontalScrollView toggle_scroll_layout;
    @ViewInject(R.id.toggle_radio_layout)
    private RadioGroup toggle_radio_layout;
    @ViewInject(R.id.toggle_veiewpager)
    private ViewPager toggle_veiewpager;

    private List<Fragment> fragmentList;
    private ToggleMenuFragmentAdapter menuFragmentAdapter;

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_toggle_menu;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {
        init(inflater);
    }


    private void init(LayoutInflater inflater) {
        List<MenuTab> menuTabList = MenuParser.getInstance().parseMenuTabList();
        if (menuTabList != null && !menuTabList.isEmpty()) {
            fragmentList = new ArrayList<>();
            for (int i = 0; i < menuTabList.size(); i++) {
                ToggleMenuSubFragment fragment = new ToggleMenuSubFragment();
                Bundle args = new Bundle();
                args.putSerializable("title", menuTabList.get(i).getName());
                args.putSerializable("subMenuList", (Serializable) menuTabList.get(i).getSubMenuList());
                fragment.setArguments(args);
                fragmentList.add(fragment);

                RadioButton rb = (RadioButton) inflater.
                        inflate(R.layout.tab_rb, null);
                rb.setId(i);
                rb.setText(menuTabList.get(i).getName());

                RadioGroup.LayoutParams params = new
                        RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT);

                toggle_radio_layout.addView(rb, params);
            }
            toggle_radio_layout.check(0);
            toggle_radio_layout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    MLog.v("Tag","toggle-check:"+checkedId);
                    toggle_veiewpager.setCurrentItem(checkedId);
                }
            });

            menuFragmentAdapter = new ToggleMenuFragmentAdapter(super.getActivity().getSupportFragmentManager(), fragmentList);
            toggle_veiewpager.setAdapter(menuFragmentAdapter);
//            toggle_veiewpager.setOffscreenPageLimit(4);
            toggle_veiewpager.setCurrentItem(0);
            toggle_veiewpager.addOnPageChangeListener(this);
        }
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTab(int idx) {
        RadioButton rb = (RadioButton) toggle_radio_layout.getChildAt(idx);
        rb.setChecked(true);
        int left = rb.getLeft();
        int width = rb.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        super.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int len = left + width / 2 - screenWidth / 2;
        toggle_scroll_layout.smoothScrollTo(len, 0);
//        MLog.v("Tag","radioGroup-width:"+toggle_radio_layout.getWidth()+" rabTab:"+rb.getWidth()+" measur:"+rb.getMeasuredWidth());

    }
}
