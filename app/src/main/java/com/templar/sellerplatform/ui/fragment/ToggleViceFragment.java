package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.parser.MenuParser;
import com.templar.sellerplatform.ui.adapter.ToggleSubmenuAdapter;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 14:40
 * 描述：${todo}
 */
public class ToggleViceFragment extends BaseFragment{
    @ViewInject(R.id.toggle_vice_lv)
    private CustomListView toggle_vice_lv;

    private ToggleSubmenuAdapter adapter;
    private List<SubMenu> viceMenuList;
    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_toggle_vice;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {

    }

    @Override
    public void initData() {
        super.initData();
        viceMenuList= MenuParser.getInstance().parseViceList();
    }

    @Override
    public void initView() {
        super.initView();

        if (viceMenuList != null && !viceMenuList.isEmpty()) {
            adapter = new ToggleSubmenuAdapter(viceMenuList, getActivity());
            toggle_vice_lv.setAdapter(adapter);
        }

    }
}
