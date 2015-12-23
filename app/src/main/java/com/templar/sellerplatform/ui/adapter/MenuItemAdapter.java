package com.templar.sellerplatform.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.entity.MenuItem;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.utils.StringUtils;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/22 11:10
 * 描述：$TODO
 */
public class MenuItemAdapter extends BaseAdapter {
    private List<MenuItem> menuItemList;
    private Context mContext;
    private int type;

    public MenuItemAdapter(List<MenuItem> menuItemList, Context mContext, int type) {
        this.menuItemList = menuItemList;
        this.mContext = mContext;
        this.type = type;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menuitem_item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final MenuItem menuItem = menuItemList.get(position);
        if (menuItem != null) {
            MLog.v("Tag", "pos:" + position + " name:" + menuItem.getItemName() + " price:" + menuItem.getItemPrice() + " menuItem" + menuItem + " size:" + menuItemList.size());
            holder.menuitem_item_name.setText(menuItem.getItemName());
            holder.menuitem_item_price.setText(menuItem.getItemPrice());
            holder.menuitem_item_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String name = s != null ? s.toString() : "";
                    menuItem.setItemName(name);
                }
            });
            holder.menuitem_item_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String price = s != null ? s.toString() : "";
                    menuItem.setItemPrice(price);
                }
            });
        }

        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.menuitem_item_name)
        private TextView menuitem_item_name;
        @ViewInject(R.id.menuitem_item_price)
        private TextView menuitem_item_price;

        public ViewHolder(View itemView) {
            ViewUtils.inject(this, itemView);
            if (1 == type)
                menuitem_item_name.setHint(StringUtils.setSpecialSize(mContext, mContext.getString(R.string.single_item_name_hint), 14));
            else
                menuitem_item_name.setHint(StringUtils.setSpecialSize(mContext, mContext.getString(R.string.vice_item_name_hint), 14));
            menuitem_item_price.setHint(StringUtils.setSpecialSize(mContext, mContext.getString(R.string.single_item_money_hint), 14));
        }
    }

    public List<MenuItem> getDataSet() {
        return this.menuItemList;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItemList.add(menuItem);
        notifyDataSetChanged();
        return;
    }
}
