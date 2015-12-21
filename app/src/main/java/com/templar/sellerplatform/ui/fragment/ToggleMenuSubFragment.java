package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.ui.adapter.ToggleSubmenuAdapter;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.widget.CustomListView;

import java.io.Serializable;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 18:16
 * 描述：${todo}
 */
public class ToggleMenuSubFragment extends BaseFragment {
    /* @ViewInject(R.id.toggle_submenu_recyclerview)
     private RecyclerView toggle_submenu_recyclerview;*/
//    private ToggleSubmenuRecyclerAdapter recyclerAdapter;
    @ViewInject(R.id.toggle_submenu_lv)
    private CustomListView toggle_submenu_lv;
    private ToggleSubmenuAdapter adapter;
    private List<SubMenu> subMenuList;

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_toggle_menu_sub;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {

    }

    @Override
    public void initData() {
        super.initData();
        if (getArguments() != null) {
            subMenuList = (List<SubMenu>) getArguments().getSerializable("subMenuList");
            MLog.v("Tag","sublist:"+(subMenuList==null));
            MLog.v("Tag","title:"+getArguments().getString("title"));
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
            adapter = new ToggleSubmenuAdapter(subMenuList, getActivity());
            toggle_submenu_lv.setAdapter(adapter);
        }

    }
}
