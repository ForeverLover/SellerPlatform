package com.templar.sellerplatform.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.StringUtils;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 18:38
 * 描述：重置管理員密碼
 */
public class ModifySuperPasswordActivity extends BaseActivity {
    @ViewInject(R.id.pwd_first_et)
    private EditText pwd_first_et;
    @ViewInject(R.id.pwd_second_et)
    private EditText pwd_second_et;


    private String pwd;
    private String tips;

    private boolean isFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_super_pwd);
    }

    @Override
    public void initData() {
        super.initData();
        isFinished=false;
    }

    @Override
    public void initListener() {
        super.initListener();
        pwd_first_et.addTextChangedListener(new PasswordInputWatcher(R.id.pwd_first_et));
        pwd_second_et.addTextChangedListener(new PasswordInputWatcher(R.id.pwd_second_et));

    }

    @OnClick({R.id.pwd_cancel_btn, R.id.pwd_ensure_btn})
    private void alterSuperPwd(View v) {
        switch (v.getId()) {
            case R.id.pwd_ensure_btn:
                if (isFinished) {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.Intent.Variable.ADMINISTRATOR_SUPER_PWD, pwd);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    showToast(tips);
                }
            case R.id.pwd_cancel_btn:
                finish();
                break;
        }

    }


    class PasswordInputWatcher implements TextWatcher {
        private int resId;

        public PasswordInputWatcher(int resId) {
            this.resId = resId;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s != null ? s.toString() : "";
            switch (resId) {
                case R.id.pwd_first_et:
                    if (!StringUtils.isEmpty(text)) {
                        tips=getString(R.string.alter_pwd_null);
                        showToast(tips);
                        return;
                    }
                    pwd = text;
                    break;
                case R.id.pwd_second_et:
                    if (StringUtils.isEmpty(text)) {
                        tips=getString(R.string.alter_confpwd_null);
                        showToast(tips);
                        return;
                    }
                    if (!pwd.equals(text)) {
                        tips=getString(R.string.alter_confpwd_eror);
                        showToast(tips);
                        return;
                    }
                    isFinished=true;
                    break;
            }
        }

    }
}
