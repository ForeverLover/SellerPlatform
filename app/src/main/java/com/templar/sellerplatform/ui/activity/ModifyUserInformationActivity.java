package com.templar.sellerplatform.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.entity.Administrator;
import com.templar.sellerplatform.entity.ResponseJson;
import com.templar.sellerplatform.net.IAPIRequests;
import com.templar.sellerplatform.net.Tasks;
import com.templar.sellerplatform.parser.UserParser;
import com.templar.sellerplatform.utils.Constants;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 15:35
 * 描述：${TODO}
 */
public class ModifyUserInformationActivity extends BaseActivity {
    @ViewInject(R.id.user_merchantId_tv)
    private TextView user_merchantId_tv;
    @ViewInject(R.id.user_account_tv)
    private TextView user_account_tv;
    @ViewInject(R.id.user_phone_tv)
    private TextView user_phone_tv;
    @ViewInject(R.id.user_pwd_et)
    private EditText user_pwd_et;

    private Administrator administrator;
    private IAPIRequests mApiRequest;

    private boolean hasChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);
    }

    @Override
    public void initData() {
        super.initData();
        administrator = /*(Administrator) getIntent().getSerializableExtra(Constants.Intent.Variable.ADMINISTRATOR_INFO)*/UserParser.getInstance().parseAdministrator();
        setDatatoView();
    }

    @Override
    public void initListener() {
        super.initListener();
        user_pwd_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s != null ? s.toString() : "";
                if (!text.equals(administrator.getPwd())) {
                    hasChanged = true;
                    administrator.setPwd(text);
                }
            }
        });
    }

    private void setDatatoView() {
        if (administrator != null) {
            user_account_tv.setText(administrator.getAccount());
            user_merchantId_tv.setText(administrator.getMerchant_id());
            user_phone_tv.setText(administrator.getPhone());
            user_pwd_et.setText(administrator.getPwd());
        }
    }

    @Override
    public void initView() {
        super.initView();
    }

    @OnClick({R.id.user_update_btn, R.id.user_cancel_btn, R.id.user_ensure_btn})
    private void operate(View v) {
        switch (v.getId()) {
            case R.id.user_update_btn:
                startActivityForResult(new Intent(this, ModifySuperPasswordActivity.class), Constants.Code.MODIFY_SUPER_CODE);
                break;
            case R.id.user_cancel_btn:
                finish();
                break;
            case R.id.user_ensure_btn:
                /*if (mApiRequest == null)
                    mApiRequest = new APIRequests(this);
                mApiRequest.alterAdministrator();*/
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && data != null) {
            switch (requestCode) {
                case Constants.Code.MODIFY_MERCHANT:
                    String superPwd = data.getStringExtra(Constants.Intent.Variable.ADMINISTRATOR_SUPER_PWD);
                    hasChanged = true;
                    administrator.setSuper_pwd(superPwd);
                    break;
            }
        }
    }

    @Override
    public void onSuccess(int taskId, Object... params) {
        super.onSuccess(taskId, params);
        ResponseJson responseJson = (ResponseJson) params[0];
        switch (taskId) {
            case Tasks.MODIFY_ADMINISTRATOR:
                showToast(getString(R.string.alter_succeed));
                finish();
                break;
        }
    }
}
