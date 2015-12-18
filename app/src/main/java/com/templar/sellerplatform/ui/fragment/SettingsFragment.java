package com.templar.sellerplatform.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.MerchantInformation;
import com.templar.sellerplatform.listener.MyTabActivityResultListener;
import com.templar.sellerplatform.parser.MerchantParser;
import com.templar.sellerplatform.ui.activity.ModifyMerchantInformationActivity;
import com.templar.sellerplatform.ui.activity.ModifyUserInformationActivity;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.DensityUtil;
import com.templar.sellerplatform.utils.MImageLoader;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.utils.StringUtils;
import com.templar.sellerplatform.widget.CornerImageView;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 11:21
 * 描述：${todo}
 */
public class SettingsFragment extends BaseFragment implements MyTabActivityResultListener {

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
    protected int getViewLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {

    }


    @Override
    public void initData() {
        super.initData();
        windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        rlParams = new RelativeLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels * 9 / 16);
        llParams = new LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels * 9 / 16 + DensityUtil.dip2px(getActivity(), 50));

        information = MerchantParser.getInstance().parseMerchantInfo();
        setData();

    }

    public void setData() {
        if (information != null) {
            MImageLoader.getInstance(getActivity()).displayImage(information.getMerchant_bg(), merchant_img_iv);
            merchant_name_tv.setText(StringUtils.getMerchantNameStr(information.getMerchant_name() + " -" + information.getMerchant_name_vice(), information.getMerchant_name().length(), getActivity(), 20, 14));
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
                getActivity().startActivityForResult(new Intent(getActivity(), ModifyMerchantInformationActivity.class).putExtra(Constants.Intent.Variable.MERCHANT_INFO, information), Constants.Code.MODIFY_MERCHANT);
                break;
            case R.id.merchant_logo_iv:
                getActivity().startActivity(new Intent(getActivity(), ModifyUserInformationActivity.class));
                break;
        }
    }


    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
        if (getActivity().RESULT_OK == resultCode && data != null) {
            switch (requestCode) {
                case Constants.Code.MODIFY_MERCHANT:
                    information = (MerchantInformation) data.getSerializableExtra(Constants.Intent.Variable.MERCHANT_INFO);
                    setData();
                    break;
            }
        }
    }




    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        Log.i(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MLog.i(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override

    public void onDestroy() {
        MLog.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        MLog.i(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        MLog.i(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        MLog.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        MLog.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onResume() {
        MLog.i(TAG, "onResume");
        super.onResume();


    }

    @Override
    public void onPause() {
        MLog.i(TAG, "onPause");
        super.onPause();
    }


    @Override


    public void onActivityCreated(Bundle savedInstanceState) {
        MLog.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);


    }
}
