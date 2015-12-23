package com.templar.sellerplatform.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.ui.activity.AddSubMenuActivivty;
import com.templar.sellerplatform.ui.adapter.ModifySubmenuAdapter;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/21 15:40
 * 描述：$TODO
 */
public class ModifySubMenuFragment extends BaseFragment {
    @ViewInject(R.id.modify_submenu_lv)
    private CustomListView modify_submenu_lv;

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

        if (subMenuList != null && !subMenuList.isEmpty()) {
            adapter = new ModifySubmenuAdapter(this,subMenuList, getActivity(), 1);
            modify_submenu_lv.setAdapter(adapter);
        }

    }

    @OnClick(R.id.menu_add_layout)
    private void addSingleMenu(View v) {
        switch (v.getId()) {
            case R.id.menu_add_layout:
                startActivityForResult(new Intent(getActivity(), AddSubMenuActivivty.class).putExtra(Constants.Intent.Variable.MENU_VICE, new SubMenu()), /*Constants.Code.ADD_MENU_SINGLE*/302);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MLog.v("Tag", "submenu_resultcode:" + resultCode + " data==null?" + (data == null) + " requestCode:" + requestCode);
        if (getActivity().RESULT_OK == resultCode && data != null) {
            SubMenu smenu = (SubMenu) data.getSerializableExtra(Constants.Intent.Variable.MENU_SINGLE);
            String id = data.getStringExtra(Constants.Intent.Variable.MENU_SINGLE_ID);
            if (smenu != null)
                switch (requestCode) {
                    case Constants.Code.ADD_MENU_SINGLE:
                        if (subMenuList == null)
                            subMenuList = new ArrayList<SubMenu>();
                        subMenuList.add(smenu);
                        if (adapter == null) {
                            adapter = new ModifySubmenuAdapter(this,subMenuList, getActivity(), 1);
                            modify_submenu_lv.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    case Constants.Code.MODIFY_MENU_SINGLE:
                        if (smenu != null) {
                            MLog.v("Tag", "current-id" + id);
                            if (adapter != null) {
                                Map<String, SubMenu> opMap = adapter.getOpSubmenuMap();
                                for (Map.Entry<String, SubMenu> entry : opMap.entrySet()) {
                                    MLog.v("Tag", "adapter-op-id" + id);
                                }

                                if (opMap != null && opMap.containsKey(id)) {
                                    SubMenu srcMenu = opMap.get(id);
                                    srcMenu.setName(smenu.getName());
                                    srcMenu.setMenuItemList(smenu.getMenuItemList());
                                    srcMenu.setNecessaryViceList(smenu.getNecessaryViceList());
                                    srcMenu.setUnnecessaryViceList(smenu.getUnnecessaryViceList());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                        break;
                }

        }

    }
}
