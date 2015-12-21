package com.templar.sellerplatform.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.templar.sellerplatform.R;
import com.templar.sellerplatform.entity.SubMenu;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/21 14:28
 * 描述：$TODO
 */
public class ToggleSubmenuAdapter extends BaseAdapter {
    private List<SubMenu> subMenuList;
    private Context mContext;

    public ToggleSubmenuAdapter(List<SubMenu> subMenuList, Context mContext) {
        this.subMenuList = subMenuList;
        this.mContext = mContext;
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
            holder = new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.toggle_sublist_item_layout,null);
            holder.toggle_sublist_item_cb = (CheckBox) convertView.findViewById(R.id.toggle_sublist_item_cb);
            holder.toggle_sublist_item_name = (TextView) convertView.findViewById(R.id.toggle_sublist_item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final SubMenu subMenu = subMenuList.get(position);
        if (subMenu != null) {
            holder.toggle_sublist_item_cb.setChecked(subMenu.isOn());
            holder.toggle_sublist_item_name.setText(subMenu.getName());
            holder.toggle_sublist_item_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    subMenu.setOn(isChecked);
                }
            });
        }

        return convertView;
    }

    class ViewHolder {
        private TextView toggle_sublist_item_name;
        private CheckBox toggle_sublist_item_cb;
    }
}
