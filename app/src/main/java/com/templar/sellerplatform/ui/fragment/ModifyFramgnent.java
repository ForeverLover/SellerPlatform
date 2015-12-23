package com.templar.sellerplatform.ui.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.listener.MyTabActivityResultListener;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 11:21
 * 描述：${todo}
 */
public class ModifyFramgnent extends BaseFragment{
    @ViewInject(R.id.modify_layout)
    private RadioGroup modify_layout;
    @ViewInject(R.id.modify_menu_rb)
    private RadioButton modify_menu_rb;
    @ViewInject(R.id.modify_vice_rb)
    private RadioButton modify_vice_rb;
    private ModifyMenuFragment modifyMenuFragment;
    private ModifyViceFragment modifyViceFragment;
    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_modify;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {

    }
    @Override
    public void initView() {
        super.initView();
        modify_layout.check(R.id.toggle_menu_rb);
        showFragment(0);
        modify_menu_rb.setTextColor(getResources().getColor(R.color.white));
        modify_menu_rb.setBackgroundResource(R.drawable.btn_special_bg);
    }

    @Override
    public void initListener() {
        super.initListener();
        modify_layout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.modify_menu_rb:
                        modify_menu_rb.setTextColor(getResources().getColor(R.color.white));
                        modify_menu_rb.setBackgroundResource(R.drawable.btn_special_bg);
                        modify_vice_rb.setTextColor(getResources().getColor(R.color.tab_colo_uncheck));
                        modify_vice_rb.setBackground(null);
                        showFragment(0);
                        break;
                    case R.id.modify_vice_rb:
                        modify_vice_rb.setTextColor(getResources().getColor(R.color.white));
                        modify_vice_rb.setBackgroundResource(R.drawable.btn_special_bg);
                        modify_menu_rb.setTextColor(getResources().getColor(R.color.tab_colo_uncheck));
                        modify_menu_rb.setBackground(null);
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
                if (modifyMenuFragment != null)
                    ft.show(modifyMenuFragment);
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    modifyMenuFragment = new ModifyMenuFragment();
                    ft.add(R.id.modify_contentLayout, modifyMenuFragment);
                }
                break;
            case 1:
                if (modifyViceFragment != null)
                    ft.show(modifyViceFragment);
                else {
                    modifyViceFragment = new ModifyViceFragment();
                    ft.add(R.id.modify_contentLayout, modifyViceFragment);
                }
                break;
        }
        ft.commit();
    }

    // 当fragment已被实例化，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {

        if (modifyMenuFragment != null)
            ft.hide(modifyMenuFragment);
        if (modifyViceFragment != null)
            ft.hide(modifyViceFragment);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment:getChildFragmentManager().getFragments()){
            if (fragment instanceof MyTabActivityResultListener){
                MyTabActivityResultListener listener= (MyTabActivityResultListener) fragment;
                listener.onTabActivityResult(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
