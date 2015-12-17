package com.templar.sellerplatform.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.entity.MerchantInformation;
import com.templar.sellerplatform.listener.MyTabActivityResultListener;
import com.templar.sellerplatform.parser.MerchantParser;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.DensityUtil;
import com.templar.sellerplatform.utils.MImageLoader;
import com.templar.sellerplatform.utils.StringUtils;
import com.templar.sellerplatform.widget.CornerImageView;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 17:14
 * 描述：$TODO
 */
public class SettingsActivity extends BaseActivity implements MyTabActivityResultListener {
    @ViewInject(R.id.merchant_title_layout)
    private RelativeLayout merchant_title_layout;
    @ViewInject(R.id.merchant_img_iv)
    private ImageView merchant_img_iv;
    @ViewInject(R.id.merchant_logo_iv)
    private CornerImageView merchant_logo_iv;
    @ViewInject(R.id.merchant_name_tv)
    private TextView merchant_name_tv;
    @ViewInject(R.id.settings_modify_layout)
    private LinearLayout settings_modify_layout;

    @ViewInject(R.id.settings_id_tv)
    private TextView settings_id_tv;
    @ViewInject(R.id.settings_addr_tv)
    private TextView settings_addr_tv;
    @ViewInject(R.id.settings_link_tv)
    private TextView settings_link_tv;
    @ViewInject(R.id.settings_ontime_tv)
    private TextView settings_ontime_tv;
    @ViewInject(R.id.settings_web_tv)
    private TextView settings_web_tv;
    @ViewInject(R.id.settings_remark_tv)
    private TextView settings_remark_tv;
    @ViewInject(R.id.settings_card_tv)
    private TextView settings_card_tv;

    private DisplayMetrics displayMetrics;
    private WindowManager windowManager;

    private RelativeLayout.LayoutParams rlParams;
    private LinearLayout.LayoutParams llParams;
    private MerchantInformation information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void initData() {
        super.initData();
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        rlParams = new RelativeLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels * 9 / 16);
        llParams = new LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels * 9 / 16 + DensityUtil.dip2px(this, 50));

        information = MerchantParser.getInstance().parseMerchantInfo();
        setData();

    }

    public void setData() {
        if (information != null) {
            MImageLoader.getInstance(this).displayImage(information.getMerchant_bg(), merchant_img_iv);
            merchant_name_tv.setText(StringUtils.getMerchantNameStr(information.getMerchant_name() + " -" + information.getMerchant_name_vice(), information.getMerchant_name().length(), this, 20, 14));
            settings_id_tv.setText(information.getMerchant_id());
            settings_addr_tv.setText(information.getMerchant_addr());
            settings_link_tv.setText(information.getMerchant_link());
            settings_ontime_tv.setText(information.getMerchant_showTime());
            settings_web_tv.setText(information.getMerchant_web());
            settings_remark_tv.setText(information.getMerchant_remark());
            settings_card_tv.setText(information.getMerchant_card());

        }
    }

    @Override
    public void initView() {
        super.initView();
        merchant_img_iv.setLayoutParams(rlParams);
        merchant_title_layout.setLayoutParams(llParams);
    }

    @OnClick({R.id.settings_modify_layout, R.id.merchant_logo_iv})
    private void operate(View v) {
        switch (v.getId()) {
            case R.id.settings_modify_layout:
                getParent().startActivityForResult(new Intent(SettingsActivity.this, ModifyMerchantInformationActivity.class).putExtra(Constants.Intent.Variable.MERCHANT_INFO, information), Constants.Code.MODIFY_MERCHANT);
                break;
            case R.id.merchant_logo_iv:
                startActivity(ModifyUserInformationActivity.class);
                break;
        }
    }


    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode && data != null) {
            switch (requestCode) {
                case Constants.Code.MODIFY_MERCHANT:
                    information = (MerchantInformation) data.getSerializableExtra(Constants.Intent.Variable.MERCHANT_INFO);
                    setData();
                    break;
            }
        }
    }
}
