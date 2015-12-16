package com.templar.sellerplatform.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 14:07
 * 描述：登录
 */
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.login_user_et)
    private EditText login_user_et;
    @ViewInject(R.id.login_pwd_et)
    private EditText login_pwd_et;

    private String userAccount;
    private String userPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.login_submit_btn)
    private void doLogin(View v) {
        if (R.id.login_submit_btn == v.getId())
            startActivity(MainActivity.class);
    }
}
