package com.templar.sellerplatform.ui.adapter;

import android.content.Context;
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

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/22 15:38
 * 描述：选项adapter
 */
public class MenuItemViceAdapter extends BaseAdapter {
    private List<SubMenu> menuItemList;
    private Context mContext;

    public MenuItemViceAdapter(List<SubMenu> menuItemList, Context mContext) {
        this.menuItemList = menuItemList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return menuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menuitem_vice_item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SubMenu subMenu=menuItemList.get(position);
        if (subMenu!=null){
            holder.menuitem_vice_name.setText(subMenu.getName());
            holder.menuitem_vice_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.menuitem_vice_name)
        private TextView menuitem_vice_name;
        @ViewInject(R.id.menuitem_vice_modify)
        private ImageView menuitem_vice_modify;

        public ViewHolder(View itemView) {
            ViewUtils.inject(this, itemView);
        }
    }
}
