package com.templar.sellerplatform.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.entity.MenuItem;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.ui.adapter.MenuItemAdapter;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.utils.StringUtils;
import com.templar.sellerplatform.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/22 20:13
 * 描述：$TODO
 */
public class AddViceAcitivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.vice_num_tv)
    private TextView vice_num_tv;
    @ViewInject(R.id.viceAdd_vice_title)
    private EditText viceAdd_vice_title;

    @ViewInject(R.id.viceAdd_item_lv)
    private ListView viceAdd_item_lv;

    @ViewInject(R.id.vice_type_spinner)
    private Spinner vice_type_spinner;
    @ViewInject(R.id.viceAdd_count_spinner)
    private Spinner viceAdd_count_spinner;

    @ViewInject(R.id.viceAdd_choice_rg)
    private RadioGroup viceAdd_choice_rg;


    private MenuItemAdapter viceItemAdapter;
    private List<MenuItem> viceItemList;

    private List<String> choiceList;
    private ArrayAdapter<String> choiceAdapter;

    private int itemNum;
    private List<String> itemList;
    private ArrayAdapter<String> itemAmountAdapter;

    /**
     * 0-必選
     * 1-非必選
     */
    private int type;

    private SubMenu subMenu;
    private int selectionType;
    private int selectioncount;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vice_add);
    }

    @Override
    public void initView() {
        super.initView();
        if (subMenu != null) {
            viceAdd_vice_title.setText(subMenu.getName());
            vice_num_tv.setText(String.valueOf(viceItemList.size()));
            viceItemAdapter = new MenuItemAdapter(viceItemList, AddViceAcitivity.this, 2);
            viceAdd_item_lv.setAdapter(viceItemAdapter);
            SystemUtils.setListViewHeight(viceAdd_item_lv);
        }
        if (1 == type) {
            viceAdd_choice_rg.check(R.id.viewAdd_choice_unecessary_rb);
        } else
            viceAdd_choice_rg.check(R.id.viewAdd_choice_necessary_rb);
        vice_type_spinner.setAdapter(choiceAdapter);
        vice_type_spinner.setSelection(selectionType);
        viceAdd_count_spinner.setAdapter(itemAmountAdapter);
        viceAdd_count_spinner.setSelection(selectioncount);
    }

    @Override
    public void initData() {
        super.initData();
        subMenu = (SubMenu) getIntent().getSerializableExtra(Constants.Intent.Variable.MENU_VICE);
        if (subMenu != null) {
            type = subMenu.getType();
            selectionType = subMenu.getSelectionType();
            selectioncount = subMenu.getSelectCount();
            viceItemList = subMenu.getMenuItemList();
        } else {
            subMenu = new SubMenu();
            subMenu.setId(System.currentTimeMillis() + "");
        }
        itemNum = viceItemList != null ? viceItemList.size() : 0;
        if (itemNum == 0) {
            viceItemList = new ArrayList<>();
            viceItemList.add(new MenuItem());

        }
        setChoiceList();
        setItemAmountList();

        choiceAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_layout, choiceList);
        choiceAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        itemAmountAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_layout, itemList);
        itemAmountAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
    }


    @Override
    public void initListener() {
        super.initListener();
        viceAdd_choice_rg.setOnCheckedChangeListener(this);
        vice_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionType = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        viceAdd_count_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectioncount = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.viceAdd_add_layout, R.id.viceAdd_ensure_btn, R.id.viceAdd_cancel_btn})
    private void doAdd(View v) {
        switch (v.getId()) {
            case R.id.viceAdd_add_layout:
                if (viceItemAdapter == null) {
                    if (viceItemList == null)
                        viceItemList = new ArrayList<>();
                    viceItemList.add(new MenuItem());
                    viceItemAdapter = new MenuItemAdapter(viceItemList, this, 2);
                    viceAdd_item_lv.setAdapter(viceItemAdapter);
                } else {
                    viceItemAdapter.addMenuItem(new MenuItem());
                }
                vice_num_tv.setText(String.valueOf(viceItemList.size()));
                itemNum = viceItemList.size();
                setItemAmountList();
                itemAmountAdapter.notifyDataSetChanged();
                SystemUtils.setListViewHeight(viceAdd_item_lv);
                break;
            case R.id.viceAdd_ensure_btn:
                title = viceAdd_vice_title.getText() != null ? viceAdd_vice_title.getText().toString() : "";
                if (StringUtils.isEmpty(title)) {
                    showToast(getString(R.string.single_title_null));
                    return;
                }
                subMenu.setName(title);
                subMenu.setType(type);
                subMenu.setSelectionType(selectionType);
                if (viceItemList == null || viceItemList.isEmpty()) {
                    showToast(getString(R.string.single_item_null));
                    return;
                }
                subMenu.setMenuItemList(removeInvalidData(viceItemList));
                if (selectioncount > subMenu.getMenuItemList().size()) {
                    showToast(getString(R.string.vice_item_more));
                    return;
                }
                subMenu.setSelectCount(selectioncount);
                Intent intent = new Intent();
                intent.putExtra(Constants.Intent.Variable.MENU_VICE, subMenu);
                intent.putExtra(Constants.Intent.Variable.MENU_VICE_ID, subMenu.getId());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.viceAdd_cancel_btn:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.viewAdd_choice_necessary_rb:
                type = 0;
                break;
            case R.id.viewAdd_choice_unecessary_rb:
                type = 1;
                break;
        }
        setChoiceList();
        choiceAdapter.notifyDataSetChanged();
    }

    public void setItemAmountList() {
        if (itemList == null)
            itemList = new ArrayList<>();
        for (int i = 0; i <= itemNum; i++) {
            if (!itemList.contains(i + ""))
                itemList.add(i + "");
        }
    }

    private void setChoiceList() {
        if (choiceList == null) {
            choiceList = new ArrayList<>();
            choiceList.add(getString(R.string.vice_more));
            choiceList.add(getString(R.string.vice_one));
        }
        if (1 == type) {
            if (choiceList.contains(getString(R.string.vice_less)))
                choiceList.remove(getString(R.string.vice_less));
        } else
            choiceList.add(1,getString(R.string.vice_less));

    }

    public List<MenuItem> removeInvalidData(List<MenuItem> sourceList) {
        List<MenuItem> resultList = null;
        if (sourceList != null && !sourceList.isEmpty()) {
            resultList = new ArrayList<MenuItem>();
            for (MenuItem menuItem : sourceList) {
                MLog.v("Tag", "name:" + menuItem.getItemName() + "　price:" + menuItem.getItemPrice());
                if (!StringUtils.isEmpty(menuItem.getItemName()) && !StringUtils.isEmpty(menuItem.getItemPrice()))
                    resultList.add(menuItem);
            }
        }

        for (MenuItem menuItem : resultList) {
            MLog.v("Tag", "res-name:" + menuItem.getItemName() + "　price:" + menuItem.getItemPrice());
        }
        return resultList;
    }

}
