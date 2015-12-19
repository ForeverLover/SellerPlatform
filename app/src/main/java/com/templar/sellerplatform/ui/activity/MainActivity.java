/*
package com.templar.sellerplatform.ui.activity;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.listener.MyTabActivityResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {
    @ViewInject(R.id.main_tabhost)
    private TabHost main_tabhost;
    private LocalActivityManager localActivityManager;

    private List<ImageView> imageList;
    private List<TextView> textViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localActivityManager = new LocalActivityManager(MainActivity.this, true);
        localActivityManager.dispatchCreate(savedInstanceState);
        initTabContent();

    }


    private void initTabContent() {
        imageList = new ArrayList<ImageView>();
        textViewList = new ArrayList<TextView>();

        main_tabhost.setup(localActivityManager);
        Intent noticeIntent = new Intent(this, DynamicActivity.class);
        main_tabhost.addTab(main_tabhost.newTabSpec("dynamin").setContent(noticeIntent).setIndicator(composeLayout(R.mipmap.dynamic_img_checked, R.string.tab_dynamic)));

        Intent toggleIntent = new Intent(this, ToggleActivity.class);
        main_tabhost.addTab(main_tabhost.newTabSpec("toggle").setContent(toggleIntent).setIndicator(composeLayout(R.mipmap.toogle_img_uncheck, R.string.tab_toogle)));

        Intent editIntent = new Intent(this, EditActivity.class);
        main_tabhost.addTab(main_tabhost.newTabSpec("edit").setContent(editIntent).setIndicator(composeLayout(R.mipmap.edit_img_uncheck, R.string.tab_edit)));

        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        main_tabhost.addTab(main_tabhost.newTabSpec("settings").setContent(settingsIntent).setIndicator(composeLayout(R.mipmap.settings_img_uncheck, R.string.tab_settings)));

        main_tabhost.setCurrentTab(0);
        main_tabhost.setOnTabChangedListener(this);
    }

    private View composeLayout(int imgId, int textId) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_bottom, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_img);
        TextView textView = (TextView) view.findViewById(R.id.tab_name);
        imageView.setImageResource(imgId);
        imageList.add(imageView);
        textViewList.add(textView);
        textView.setText(getString(textId));
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        imageList.get(0).setImageResource(R.mipmap.dynamic_img_uncheck);
        imageList.get(1).setImageResource(R.mipmap.toogle_img_uncheck);
        imageList.get(2).setImageResource(R.mipmap.edit_img_uncheck);
        imageList.get(3).setImageResource(R.mipmap.settings_img_uncheck);
        if ("dynamin".equals(tabId)) {
            imageList.get(0).setImageResource(R.mipmap.dynamic_img_checked);
        }
        if ("toggle".equals(tabId)) {
            imageList.get(1).setImageResource(R.mipmap.toggle_img_checked);
        }
        if ("edit".equals(tabId)) {
            imageList.get(2).setImageResource(R.mipmap.edit_img_checked);
        }
        if ("settings".equals(tabId)) {
            imageList.get(3).setImageResource(R.mipmap.settings_img_checked);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取当前活动的Activity实例
        Activity subActivity = localActivityManager.getCurrentActivity();
        //判断是否实现返回值接口
        if (subActivity instanceof MyTabActivityResultListener) {
            //获取返回值接口实例
            MyTabActivityResultListener listener = (MyTabActivityResultListener) subActivity;
            //转发请求到子Activity
            listener.onTabActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
*/
