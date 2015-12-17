package com.templar.sellerplatform.config;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lidroid.xutils.ViewUtils;
import com.templar.sellerplatform.R;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 12:30
 * 描述：带背景的类
 */
public class BGActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSuperContentView(R.layout.activity_bg);
    }

    @Override
    public void setContentView(int layoutResID) {
        View viewContent = View.inflate(this, layoutResID, null);
        ((FrameLayout) findViewById(R.id.title_frame_content))
                .addView(viewContent);
        ViewUtils.inject(this);
        init();
    }

    @Override
    public void setContentView(View view) {
        ((FrameLayout) findViewById(R.id.title_frame_content)).addView(view);
        ViewUtils.inject(this);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        ((FrameLayout) findViewById(R.id.title_frame_content)).addView(view);
        ViewUtils.inject(this);
        init();
    }
}
