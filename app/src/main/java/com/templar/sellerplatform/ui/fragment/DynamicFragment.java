package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.ui.adapter.DynamicPagerAdapter;
import com.templar.sellerplatform.widget.PageIndicator;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 11:18
 * 描述：${TODO}
 */
public class DynamicFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener{
    @ViewInject(R.id.dynamic_viewpager)
    private ViewPager dynamic_viewpager;
    @ViewInject(R.id.dynamic_content_indicatore)
    private PageIndicator dynamic_content_indicatore;
    @ViewInject(R.id.dynamic_cb)
    private CheckBox dynamic_cb;

    @ViewInject(R.id.dynamic_notice_btn)
    private RadioButton dynamic_notice_btn;
    @ViewInject(R.id.dynamic_deal_btn)
    private RadioButton dynamic_deal_btn;
    @ViewInject(R.id.dynamic_wait_btn)
    private RadioButton dynamic_wait_btn;
    @ViewInject(R.id.dynamic_end_btn)
    private RadioButton dynamic_end_btn;

    @ViewInject(R.id.dynamic_rg)
    private RadioGroup dynamic_rg;

    private DynamicPagerAdapter dynamicPagerAdapter;

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {

    }





    @Override
    public void initData() {
        super.initData();
//        super.getActivity().getSupportFragmentManager()
        dynamicPagerAdapter = new DynamicPagerAdapter(getActivity().getSupportFragmentManager());
    }

    @Override
    public void initView() {
        super.initView();

        dynamic_viewpager.setAdapter(dynamicPagerAdapter);
        dynamic_content_indicatore.setViewPager(dynamic_viewpager);

    }

    @Override
    public void initListener() {
        super.initListener();

        dynamic_cb.setOnCheckedChangeListener(this);
        dynamic_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        dynamic_rg.check(R.id.dynamic_notice_btn);
                        break;
                    case 1:
                        dynamic_rg.check(R.id.dynamic_deal_btn);
                        break;
                    case 2:
                        dynamic_rg.check(R.id.dynamic_wait_btn);
                        break;
                    case 3:
                        dynamic_rg.check(R.id.dynamic_end_btn);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        dynamic_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.dynamic_notice_btn:
                        dynamic_viewpager.setCurrentItem(0);
                        break;
                    case R.id.dynamic_deal_btn:
                        dynamic_viewpager.setCurrentItem(1);
                        break;
                    case R.id.dynamic_wait_btn:
                        dynamic_viewpager.setCurrentItem(2);
                        break;
                    case R.id.dynamic_end_btn:
                        dynamic_viewpager.setCurrentItem(3);
                        break;
                }
            }
        });

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
