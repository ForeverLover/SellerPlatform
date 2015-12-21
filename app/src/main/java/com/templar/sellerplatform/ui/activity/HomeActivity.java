package com.templar.sellerplatform.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.listener.MyTabActivityResultListener;
import com.templar.sellerplatform.utils.TabUtils;
import com.templar.sellerplatform.widget.MyFragmentTabHost;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 11:09
 * 描述：${TODO}
 */
public class HomeActivity extends BaseActivity implements TabHost.OnTabChangeListener{
    private MyFragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabHost = (MyFragmentTabHost)findViewById(R.id.my_tabhost);
        tabHost.setup(this, super.getSupportFragmentManager()
                , R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();

    }

    private void initTab() {
        for(int i=0;i< TabUtils.MainTab.TabFragment.length;i++){
            TabHost.TabSpec tabSpec=tabHost.newTabSpec(getString(TabUtils.MainTab.tabsTextId[i])).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabUtils.MainTab.TabFragment[i],null);
            tabHost.setTag(i);
        }
    }

    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_bottom, null);
        ((TextView)view.findViewById(R.id.tab_name)).setText(getString(TabUtils.MainTab.tabsTextId[idx]));
        if (idx == 0) {
            ((ImageView) view.findViewById(R.id.tab_img)).setImageResource(TabUtils.MainTab.tabsCheckedImg[idx]);
        } else {
            ((ImageView) view.findViewById(R.id.tab_img)).setImageResource( TabUtils.MainTab.tabUncheckImg[idx]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        // TODO Auto-generated method stub
        updateTab();

    }

    private void updateTab() {
        TabWidget tabw = tabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            ImageView iv = (ImageView) view.findViewById(R.id.tab_img);
            if (i == tabHost.getCurrentTab()) {
                iv.setImageResource(TabUtils.MainTab.tabsCheckedImg[i]);
            } else {
                iv.setImageResource( TabUtils.MainTab.tabUncheckImg[i]);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // 获取当前活动的Activity实例
//        Activity subActivity = new LocalActivityManager(HomeActivity.this, true).getCurrentActivity();
//        //判断是否实现返回值接口
//        if (subActivity instanceof MyTabActivityResultListener) {
//            //获取返回值接口实例
//            MyTabActivityResultListener listener = (MyTabActivityResultListener) subActivity;
//            //转发请求到子Activity
//            listener.onTabActivityResult(requestCode, resultCode, data);
//        }
        for (Fragment fragment:getSupportFragmentManager().getFragments()){
            if (fragment instanceof  MyTabActivityResultListener){
                MyTabActivityResultListener listener= (MyTabActivityResultListener) fragment;
                listener.onTabActivityResult(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);



    }
}
