package com.templar.sellerplatform.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseActivity;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.parser.MenuParser;
import com.templar.sellerplatform.ui.adapter.ViceQAAdapter;
import com.templar.sellerplatform.utils.Constants;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/22 15:46
 * 描述：$TODO
 */
public class ViceQAConfigActivity extends BaseActivity {
    @ViewInject(R.id.menuitem_vice_lv)
    private CustomListView menuitem_vice_lv;
    @ViewInject(R.id.menuitem_vice_finishBtn)
    private TextView menuitem_vice_finishBtn;

    private List<SubMenu> subMenuList;
    private ViceQAAdapter qaAdapter;

    private SubMenu subMenu;
    private Map<String, SubMenu> selectedMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vice_config);
    }

    @Override
    public void initData() {
        super.initData();
        subMenuList = MenuParser.getInstance().parseViceList();
        subMenu = (SubMenu) getIntent().getSerializableExtra(Constants.Intent.Variable.MENU_VICE);
        if (subMenu != null) {
            selectedMap = new HashMap<String, SubMenu>();
            MLog.v("Tag", "necessary:" + (subMenu.getNecessaryViceList() != null && !subMenu.getNecessaryViceList().isEmpty()));
            if (subMenu.getNecessaryViceList() != null && !subMenu.getNecessaryViceList().isEmpty())
                for (SubMenu menu : subMenu.getNecessaryViceList()) {
                    selectedMap.put(menu.getId(), menu);
                }
            MLog.v("Tag", "necessary:" + (subMenu.getUnnecessaryViceList() != null && !subMenu.getUnnecessaryViceList().isEmpty()));
            if (subMenu.getUnnecessaryViceList() != null && !subMenu.getUnnecessaryViceList().isEmpty())
                for (SubMenu menu : subMenu.getUnnecessaryViceList()) {
                    selectedMap.put(menu.getId(), menu);
                }
        }
        if (subMenuList != null) {
            qaAdapter = new ViceQAAdapter(subMenuList, this);
            qaAdapter.setAddViceMap(selectedMap);
        }

    }

    @Override
    public void initView() {
        super.initView();
        if (qaAdapter != null)
            menuitem_vice_lv.setAdapter(qaAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        menuitem_vice_finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qaAdapter != null) {
                    selectedMap = qaAdapter.getAddViceMap();
                    dealWithData();
                }
                finish();
            }
        });
    }

    private void dealWithData() {
        subMenu.setNecessaryViceList(new ArrayList<SubMenu>());
        subMenu.setUnnecessaryViceList(new ArrayList<SubMenu>());
        for (Map.Entry<String, SubMenu> entry : selectedMap.entrySet()) {
            if (entry.getValue() != null)
                switch (entry.getValue().getType()) {
                    case 0:
                        subMenu.getNecessaryViceList().add(entry.getValue());
                        break;
                    case 1:
                        subMenu.getUnnecessaryViceList().add(entry.getValue());
                        break;
                }
        }
        Intent intent = new Intent();
        intent.putExtra(Constants.Intent.Variable.MENU_VICE, subMenu);
        setResult(RESULT_OK, intent);


    }
}
