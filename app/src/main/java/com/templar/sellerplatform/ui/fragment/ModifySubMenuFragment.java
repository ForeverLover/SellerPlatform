package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.ui.adapter.ModifySubmenuAdapter;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/21 15:40
 * 描述：$TODO
 */
public class ModifySubMenuFragment extends BaseFragment{
    @ViewInject(R.id.modify_submenu_lv)
    private CustomListView modify_submenu_lv;
    @ViewInject(R.id.menu_add_layout)
    private LinearLayout menu_add_layout;
    private ModifySubmenuAdapter adapter;
    private List<SubMenu> subMenuList;

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_modify_menu_sub;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {
    }

    @Override
    public void initData() {
        super.initData();
        if (getArguments() != null) {
            subMenuList = (List<SubMenu>) getArguments().getSerializable("subMenuList");
        }

    }


    @Override
    public void initView() {
        super.initView();
//        if (subMenuList!=null&&!subMenuList.isEmpty()) {
//            toggle_submenu_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),
//                    LinearLayoutManager.VERTICAL, false));
//            toggle_submenu_recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
//                    LinearLayoutManager.VERTICAL, R.drawable.shape_divideline_menu));
//            recyclerAdapter = new ToggleSubmenuRecyclerAdapter(subMenuList);
//            toggle_submenu_recyclerview.setAdapter(recyclerAdapter);
//        }else{
//            toggle_submenu_recyclerview.setVisibility(View.GONE);
//        }

        if (subMenuList != null && !subMenuList.isEmpty()) {
            adapter = new ModifySubmenuAdapter(subMenuList, getActivity());
            modify_submenu_lv.setAdapter(adapter);
        }

    }
}
