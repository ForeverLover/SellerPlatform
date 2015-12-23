package com.templar.sellerplatform.ui.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.ui.activity.AddSubMenuActivivty;
import com.templar.sellerplatform.ui.activity.AddViceAcitivity;
import com.templar.sellerplatform.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/21 15:33
 * 描述：$TODO
 */
public class ModifySubmenuAdapter extends BaseAdapter {
    private List<SubMenu> subMenuList;
    private Activity mContext;
    /**
     * 1-编辑菜单列表
     * 2-编辑副选项列表
     */
    private int select;
    private Fragment fragment;

    private Map<String, SubMenu> opSubmenuMap;

    public ModifySubmenuAdapter(Fragment fragment,List<SubMenu> subMenuList, Activity mContext, int select) {
        this.subMenuList = subMenuList;
        this.mContext = mContext;
        this.select = select;
        this.fragment=fragment;

    }

    @Override
    public int getCount() {
        return subMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return subMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.modify_menu_item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final SubMenu subMenu = subMenuList.get(position);
        if (subMenu != null) {
            holder.modify_name_tv.setText(subMenu.getName());
            holder.modify_delete_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subMenuList.remove(subMenu);
                    notifyDataSetChanged();
                }
            });
            holder.modify_upate_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (opSubmenuMap == null)
                        opSubmenuMap = new HashMap<String, SubMenu>();
                    opSubmenuMap.put(subMenu.getId(), subMenu);
                    if (1 == select) {
                        fragment.startActivityForResult(new Intent(mContext, AddSubMenuActivivty.class).putExtra(Constants.Intent.Variable.MENU_SINGLE, subMenu), Constants.Code.MODIFY_MENU_SINGLE);
                        return;
                    }
                    if (2 == select)
                        fragment.startActivityForResult(new Intent(mContext, AddViceAcitivity.class).putExtra(Constants.Intent.Variable.MENU_VICE, subMenu), Constants.Code.MODIFY_MENU_VICE);
                }
            });
        }

        return convertView;
    }

    public Map<String, SubMenu> getOpSubmenuMap() {
        return this.opSubmenuMap;
    }

    class ViewHolder {
        @ViewInject(R.id.modify_name_tv)
        private TextView modify_name_tv;
        @ViewInject(R.id.modify_delete_img)
        private ImageView modify_delete_img;
        @ViewInject(R.id.modify_upate_img)
        private ImageView modify_upate_img;

        public ViewHolder(View itemView) {
            ViewUtils.inject(this, itemView);
        }


    }
}
