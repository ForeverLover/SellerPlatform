package com.templar.sellerplatform.ui.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();

        float density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）  
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）  
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        Log.e("Tag" + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e("Tag" + "  DisplayMetrics", "density=" + density + "; densityDPI=" + densityDPI);

       int  screenWidth  = dm.widthPixels;      // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels;     // 屏幕高（像素，如：800px）

        Log.e("Tag","screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);

    }

    @OnClick(R.id.login_submit_btn)
    private void doLogin(View v) {
        if (R.id.login_submit_btn == v.getId())
            startActivity(HomeActivity.class);
//            startActivity(MainActivity.class);
    }
}
