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
import com.templar.sellerplatform.entity.MerchantInformation;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.MLog;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 10:07
 * 描述：修改店铺信息
 */
public class ModifyMerchantInformationActivity extends BaseActivity {
    @ViewInject(R.id.modify_merchant_id)
    private TextView modify_merchant_id;

    @ViewInject(R.id.modify_merchant_name)
    private EditText modify_merchant_name;
    @ViewInject(R.id.modify_merchant_vicename)
    private EditText modify_merchant_vicename;
    @ViewInject(R.id.modify_merchant_addr)
    private EditText modify_merchant_addr;
    @ViewInject(R.id.modify_merchant_link)
    private EditText modify_merchant_link;
    @ViewInject(R.id.modify_merchant_showTime)
    private EditText modify_merchant_showTime;
    @ViewInject(R.id.modify_merchant_web)
    private EditText modify_merchant_web;
    @ViewInject(R.id.modify_merchant_card)
    private EditText modify_merchant_card;
    @ViewInject(R.id.modify_merchant_remark)
    private EditText modify_merchant_remark;

    @ViewInject(R.id.modify_merchant_save_btn)
    private TextView modify_merchant_save_btn;

    private MerchantInformation originInformation;

    private boolean hasChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_merchant);

    }

    @Override
    public void initData() {
        super.initData();
        originInformation= (MerchantInformation) getIntent().getSerializableExtra(Constants.Intent.Variable.MERCHANT_INFO);

    }

    @Override
    public void initListener() {
        super.initListener();
        modify_merchant_name.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_name));
        modify_merchant_vicename.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_vicename));
        modify_merchant_addr.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_addr));
        modify_merchant_link.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_link));
        modify_merchant_showTime.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_showTime));
        modify_merchant_web.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_web));
        modify_merchant_card.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_card));
        modify_merchant_remark.addTextChangedListener(new MyTextWatcher(R.id.modify_merchant_remark));
    }

    @Override
    public void initView() {
        super.initView();
        if (originInformation!=null){
            modify_merchant_name.setText(originInformation.getMerchant_name());
            modify_merchant_vicename.setText(originInformation.getMerchant_name_vice());
            modify_merchant_addr.setText(originInformation.getMerchant_addr());
            modify_merchant_link.setText(originInformation.getMerchant_link());
            modify_merchant_showTime.setText(originInformation.getMerchant_showTime());
            modify_merchant_web.setText(originInformation.getMerchant_web());
            modify_merchant_card.setText(originInformation.getMerchant_card());
            modify_merchant_remark.setText(originInformation.getMerchant_remark());
            modify_merchant_id.setText(originInformation.getMerchant_id());
        }else{
            showToast(getString(R.string.data_error));
            finish();
        }
    }

    @OnClick(R.id.modify_merchant_save_btn)
    private void saveMerchantChanges(View v){
        if (hasChanged){
            showToast("update succeed");
            Intent intent=new Intent();
            intent.putExtra(Constants.Intent.Variable.MERCHANT_INFO, originInformation);
            MLog.v("Tag", "after-edit_before-finish:" + originInformation.getMerchant_name_vice());
            setResult(RESULT_OK,intent);
        }
        finish();


    }

    class MyTextWatcher implements TextWatcher {
        private int resId;

        public MyTextWatcher(int resId) {
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
                case R.id.modify_merchant_name:
                    if (!text.equals(originInformation.getMerchant_name())) {
                        hasChanged = true;
                        originInformation.setMerchant_name(text);
                    }
                    break;
                case R.id.modify_merchant_vicename:
                    if (!text.equals(originInformation.getMerchant_name_vice())) {
                        hasChanged = true;
                        MLog.v("Tag","after-edit:"+text);
                        originInformation.setMerchant_name_vice(text);
                    }
                    break;
                case R.id.modify_merchant_addr:
                    if (!text.equals(originInformation.getMerchant_addr())) {
                        hasChanged = true;
                        originInformation.setMerchant_addr(text);
                    }
                    break;
                case R.id.modify_merchant_link:
                    if (!text.equals(originInformation.getMerchant_link())) {
                        hasChanged = true;
                        originInformation.setMerchant_link(text);
                    }
                    break;
                case R.id.modify_merchant_showTime:
                    if (!text.equals(originInformation.getMerchant_showTime())) {
                        hasChanged = true;
                        originInformation.setMerchant_showTime(text);
                    }
                    break;
                case R.id.modify_merchant_web:
                    if (!text.equals(originInformation.getMerchant_web())) {
                        hasChanged = true;
                        originInformation.setMerchant_web(text);
                    }
                    break;
                case R.id.modify_merchant_card:
                    if (!text.equals(originInformation.getMerchant_card())) {
                        hasChanged = true;
                        originInformation.setMerchant_card(text);
                    }
                    break;
                case R.id.modify_merchant_remark:
                    if (!text.equals(originInformation.getMerchant_remark())) {
                        hasChanged = true;
                        originInformation.setMerchant_remark(text);
                    }
                    break;
            }
        }

    }
}
