package com.templar.sellerplatform.config;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blueware.agent.android.BlueWare;
import com.lidroid.xutils.ViewUtils;
import com.templar.sellerplatform.net.XRequestCallBack;
import com.templar.sellerplatform.utils.Constants;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 14:17
 * 描述：基类
 */
public class BaseActivity extends AppCompatActivity implements XRequestCallBack, Init {
    private boolean destroyed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        destroyed = false;
        AppManager.getInstance().addActivity(this);
        BlueWare.withApplicationToken(Constants.Key.ONE_APM).start(this.getApplication());
    }

    protected void init() {
        initData();
        initView();
        initListener();
    }

    protected void setSuperContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void setSuperContentView(View view) {
        super.setContentView(view);
    }

       @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ViewUtils.inject(this);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ViewUtils.inject(this);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ViewUtils.inject(this);
        init();
    }


    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void onPrepare(int taskId) {

    }

    @Override
    public void onSuccess(int taskId, Object... params) {

    }

    @Override
    public void onSuccess(int taskId, String flag, Object... params) {

    }

    @Override
    public void onEnd(int taskId) {

    }

    @Override
    public void onFailed(int taskId, int errorCode, String errorMsg) {

    }

    @Override
    public void onLoading(int taskId, long count, long current) {

    }

    @Override
    public boolean isCallBack() {
        return !destroyed;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }
}
