package com.templar.sellerplatform.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.MenuItem;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.listener.MyTabActivityResultListener;
import com.templar.sellerplatform.parser.MenuParser;
import com.templar.sellerplatform.ui.activity.AddViceAcitivity;
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
 * 创建时间：2015/12/18 17:12
 * 描述：${todo}
 */
public class ModifyViceFragment extends BaseFragment implements MyTabActivityResultListener {
    @ViewInject(R.id.modify_vice_lv)
    private CustomListView modify_vice_lv;

    private ModifySubmenuAdapter adapter;
    private List<SubMenu> viceMenuList;

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
        viceMenuList = MenuParser.getInstance().parseViceList();
    }

    @Override
    public void initView() {
        super.initView();

        if (viceMenuList != null && !viceMenuList.isEmpty()) {
            adapter = new ModifySubmenuAdapter(getParentFragment(), viceMenuList, getActivity(), 2);
            modify_vice_lv.setAdapter(adapter);
        }
    }

    @OnClick(R.id.vice_add_layout)
    private void doAdd(View v) {
        switch (v.getId()) {
            case R.id.vice_add_layout:
                getParentFragment().startActivityForResult(new Intent(getActivity(), AddViceAcitivity.class), Constants.Code.ADD_VICE);
                break;
        }
    }


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }*/

    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
        MLog.v("Tag", "vice_resultcode:" + resultCode + " data==null?" + (data == null) + " requestCode:" + requestCode);
        if (getActivity().RESULT_OK == resultCode & data != null) {
            SubMenu subMenu = (SubMenu) data.getSerializableExtra(Constants.Intent.Variable.MENU_VICE);
            String subMenuId = data.getStringExtra(Constants.Intent.Variable.MENU_VICE_ID);
            switch (requestCode) {
                case Constants.Code.ADD_VICE:
                    if (subMenu != null) {
                        for (MenuItem menuItem : subMenu.getMenuItemList()) {
                            MLog.v("Tag","add-name:"+menuItem.getItemName()+"　price:"+menuItem.getItemPrice());
                        }
                        if (adapter == null) {
                            if (viceMenuList == null)
                                viceMenuList = new ArrayList<>();
                            viceMenuList.add(subMenu);
                            adapter = new ModifySubmenuAdapter(getParentFragment(), viceMenuList, getActivity(), 2);
                            modify_vice_lv.setAdapter(adapter);
                        } else {
                            viceMenuList.add(subMenu);
                            adapter.notifyDataSetChanged();
                        }
                    }
                    break;
                case Constants.Code.MODIFY_MENU_VICE:
                    MLog.v("Tag", "current-id" + subMenuId);
                    if (subMenu != null) {
                        if (adapter != null) {
                            Map<String, SubMenu> opMap = adapter.getOpSubmenuMap();
                            for (Map.Entry<String, SubMenu> entry : opMap.entrySet()) {
                                MLog.v("Tag", "adapter-op-id" + subMenuId);
                            }
                            if (opMap != null && opMap.containsKey(subMenuId)) {
                                SubMenu srcMenu = opMap.get(subMenuId);
                                srcMenu.setName(subMenu.getName());
                                srcMenu.setType(subMenu.getType());
                                srcMenu.setMenuItemList(subMenu.getMenuItemList());
                                srcMenu.setSelectCount(subMenu.getSelectCount());
                                srcMenu.setSelectionType(subMenu.getSelectionType());
                                for (MenuItem menuItem : subMenu.getMenuItemList()) {
                                    MLog.v("Tag", "update-name:" + menuItem.getItemName() + "　price:" + menuItem.getItemPrice());
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
