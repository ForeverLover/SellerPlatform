package com.templar.sellerplatform.ui.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 11:20
 * 描述：${TODO}
 */
public class ToggleFragment extends BaseFragment{

    @ViewInject(R.id.toggle_layout)
    private RadioGroup toggle_layout;
    @ViewInject(R.id.toggle_menu_rb)
    private RadioButton toggle_menu_rb;
    @ViewInject(R.id.toggle_vice_rb)
    private RadioButton toggle_vice_rb;

    private ToggleMenuFragment toggleMenuFragment;
    private ToggleViceFragment togglViceFragment;


    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_toggle;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {

    }



    @Override
    public void initView() {
        super.initView();
        toggle_layout.check(R.id.toggle_menu_rb);
        showFragment(0);
        toggle_menu_rb.setTextColor(getResources().getColor(R.color.white));
        toggle_menu_rb.setBackgroundResource(R.drawable.btn_special_bg);
    }

    @Override
    public void initListener() {
        super.initListener();
        toggle_layout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.toggle_menu_rb:
                        toggle_menu_rb.setTextColor(getResources().getColor(R.color.white));
                        toggle_menu_rb.setBackgroundResource(R.drawable.btn_special_bg);
                        toggle_vice_rb.setTextColor(getResources().getColor(R.color.tab_colo_uncheck));
                        toggle_vice_rb.setBackground(null);
                        showFragment(0);
                        break;
                    case R.id.toggle_vice_rb:
                        toggle_vice_rb.setTextColor(getResources().getColor(R.color.white));
                        toggle_vice_rb.setBackgroundResource(R.drawable.btn_special_bg);
                        toggle_menu_rb.setTextColor(getResources().getColor(R.color.tab_colo_uncheck));
                        toggle_menu_rb.setBackground(null);
                        showFragment(1);
                        break;
                }
            }
        });
    }


    public void showFragment(int index) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(ft);

        switch (index) {
            case 0:
                // 如果fragment1已经存在则将其显示出来
                if (toggleMenuFragment != null)
                    ft.show(toggleMenuFragment);
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    toggleMenuFragment = new ToggleMenuFragment();
                    ft.add(R.id.toggle_contentLayout, toggleMenuFragment);
                }
                break;
            case 1:
                if (togglViceFragment != null)
                    ft.show(togglViceFragment);
                else {
                    togglViceFragment = new ToggleViceFragment();
                    ft.add(R.id.toggle_contentLayout, togglViceFragment);
                }
                break;
        }
        ft.commit();
    }

    // 当fragment已被实例化，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {
        if (toggleMenuFragment != null)
            ft.hide(toggleMenuFragment);
        if (togglViceFragment != null)
            ft.hide(togglViceFragment);

    }

}
