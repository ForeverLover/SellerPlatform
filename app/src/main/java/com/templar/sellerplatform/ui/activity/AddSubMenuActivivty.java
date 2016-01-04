package com.templar.sellerplatform.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.entity.MenuItem;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.ui.adapter.MenuItemAdapter;
import com.templar.sellerplatform.ui.adapter.MenuItemViceAdapter;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.utils.StringUtils;
import com.templar.sellerplatform.utils.SystemUtils;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/21 17:48
 * 描述：添加菜单单品
 */
public class AddSubMenuActivivty extends BaseActivity {
    @ViewInject(R.id.menu_single_title)
    private EditText menu_single_title;
    @ViewInject(R.id.menu_single_lv)
    private ListView menu_single_lv;

    @ViewInject(R.id.menu_vice_necessary_layout)
    private LinearLayout menu_vice_necessary_layout;
    @ViewInject(R.id.menu_vice_unnecessary_layout)
    private LinearLayout menu_vice_unnecessary_layout;
    @ViewInject(R.id.menu_vice_necessary_lv)
    private CustomListView menu_vice_necessary_lv;
    @ViewInject(R.id.menu_vice_unnecessary_lv)
    private CustomListView menu_vice_unnecessary_lv;

    private MenuItemAdapter menuItemAdapter;
    private List<MenuItem> menuSingleItemList;

    private SubMenu subMenu;
    private List<SubMenu> necessaryList;
    private List<SubMenu> unnecessaryList;
    private MenuItemViceAdapter necessaryViceAdapter;
    private MenuItemViceAdapter unnecessaryViceAdapter;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
    }

    @Override
    public void initData() {
        super.initData();
        subMenu = (SubMenu) getIntent().getSerializableExtra(Constants.Intent.Variable.MENU_SINGLE);


    }

    @Override
    public void initView() {
        super.initView();
        if (subMenu != null) {
            menu_single_title.setText(subMenu.getName());
            menuSingleItemList = subMenu.getMenuItemList();
            setViceData(subMenu);
        } else {
            subMenu = new SubMenu();
            subMenu.setId(System.currentTimeMillis() + "");
        }
        if (menuSingleItemList == null)
            menuSingleItemList = new ArrayList<>();
        if (menuSingleItemList.isEmpty())
            menuSingleItemList.add(new MenuItem());
        menuItemAdapter = new MenuItemAdapter(menuSingleItemList, AddSubMenuActivivty.this, 1);
        menu_single_lv.setAdapter(menuItemAdapter);
    }

    private void setViceData(SubMenu sMenu) {
        necessaryList = sMenu.getNecessaryViceList();
        unnecessaryList = sMenu.getUnnecessaryViceList();
        subMenu.setNecessaryViceList(necessaryList);
        subMenu.setUnnecessaryViceList(unnecessaryList);
        if (necessaryList != null && !necessaryList.isEmpty()) {
            menu_vice_necessary_layout.setVisibility(View.VISIBLE);
            if (necessaryViceAdapter == null) {
                necessaryViceAdapter = new MenuItemViceAdapter(necessaryList, this);
                menu_vice_necessary_lv.setAdapter(necessaryViceAdapter);
            } else
                necessaryViceAdapter.notifyDataSetChanged();
        } else {
            menu_vice_necessary_layout.setVisibility(View.GONE);
        }
        if (unnecessaryList != null && !unnecessaryList.isEmpty()) {
            menu_vice_unnecessary_layout.setVisibility(View.VISIBLE);
            if (unnecessaryViceAdapter == null) {
                unnecessaryViceAdapter = new MenuItemViceAdapter(unnecessaryList, this);
                menu_vice_unnecessary_lv.setAdapter(unnecessaryViceAdapter);
            } else
                unnecessaryViceAdapter.notifyDataSetChanged();
        } else {
            menu_vice_unnecessary_layout.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.menu_item_add_layout, R.id.menu_single_ensure_btn, R.id.menu_single_cancel_btn, R.id.menu_vice_add_layout})
    private void doAdd(View v) {
        switch (v.getId()) {
            case R.id.menu_item_add_layout:
                if (menuItemAdapter == null) {
                    if (menuSingleItemList == null)
                        menuSingleItemList = new ArrayList<>();
                    menuSingleItemList.add(new MenuItem());
                    menuItemAdapter = new MenuItemAdapter(menuSingleItemList, AddSubMenuActivivty.this, 1);
                    menu_single_lv.setAdapter(menuItemAdapter);
                    SystemUtils.setListViewHeight(menu_single_lv);
                } else {
                    menuItemAdapter.addMenuItem(new MenuItem());
                }
                SystemUtils.setListViewHeight(menu_single_lv);
                break;
            case R.id.menu_single_ensure_btn:
                title = menu_single_title.getText() != null ? menu_single_title.getText().toString() : "";
                if (StringUtils.isEmpty(title)) {
                    showToast(getString(R.string.single_title_null));
                    return;
                }
                subMenu.setName(title);
                if (menuSingleItemList == null || menuSingleItemList.isEmpty()) {
                    showToast(getString(R.string.single_item_null));
                    return;
                }
                subMenu.setMenuItemList(removeInvalidData(menuSingleItemList));

                Intent intent = new Intent();
                intent.putExtra(Constants.Intent.Variable.MENU_SINGLE, subMenu);
                intent.putExtra(Constants.Intent.Variable.MENU_SINGLE_ID, subMenu.getId());
                setResult(RESULT_OK, intent);
                MLog.v("Tag", "necessary:" + (subMenu.getNecessaryViceList() == null ? 0 : subMenu.getNecessaryViceList().size()));
                MLog.v("Tag", "unecessary:" + (subMenu.getUnnecessaryViceList() == null ? 0 : subMenu.getUnnecessaryViceList().size()));
                finish();
                break;
            case R.id.menu_single_cancel_btn:
                finish();
                break;
            case R.id.menu_vice_add_layout:
                startActivityForResult(new Intent(this, ViceQAConfigActivity.class).putExtra(Constants.Intent.Variable.MENU_VICE, subMenu), Constants.Code.ADD_MENU_VICE);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MLog.v("Tag", "requestcode:" + requestCode + " resultCode:" + resultCode + " " + (data == null));
        if (RESULT_OK == resultCode && data != null)
            switch (requestCode) {
                case Constants.Code.ADD_MENU_VICE:
                    SubMenu smenu = (SubMenu) data.getSerializableExtra(Constants.Intent.Variable.MENU_VICE);
                    if (smenu != null) {
                        setViceData(smenu);
                    } else
                        MLog.v("Tag", "submenu is null");
                    break;
            }

    }

    public List<MenuItem> removeInvalidData(List<MenuItem> sourceList) {
        List<MenuItem> resultList = null;
        if (sourceList != null && !sourceList.isEmpty()) {
            resultList = new ArrayList<MenuItem>();
            for (MenuItem menuItem : sourceList) {
                if (!StringUtils.isEmpty(menuItem.getItemName()) && !StringUtils.isEmpty(menuItem.getItemPrice()))
                    resultList.add(menuItem);
            }
        }
        return resultList;
    }
}
