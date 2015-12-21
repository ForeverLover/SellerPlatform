package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.parser.MenuParser;
import com.templar.sellerplatform.ui.adapter.ModifySubmenuAdapter;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 17:12
 * 描述：${todo}
 */
public class ModifyViceFragment extends BaseFragment{
    @ViewInject(R.id.modify_vice_lv)
    private CustomListView modify_vice_lv;

    private ModifySubmenuAdapter adapter;
    private List<SubMenu> viceMenuList;
    @ViewInject(R.id.menu_add_layout)
    private LinearLayout menu_add_layout;
    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_modify_vice;
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
            adapter = new ModifySubmenuAdapter(viceMenuList, getActivity());
            modify_vice_lv.setAdapter(adapter);
        }

    }
}
