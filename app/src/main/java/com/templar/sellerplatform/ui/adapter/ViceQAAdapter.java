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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/22 16:02
 * 描述：$TODO
 */
public class ViceQAAdapter extends BaseAdapter {
    private List<SubMenu> menuItemList;
    private Context mContext;
    private Map<String, SubMenu> addViceMap;

    public ViceQAAdapter(List<SubMenu> menuItemList, Context mContext) {
        this.menuItemList = menuItemList;
        this.mContext = mContext;
    }

    public Map<String, SubMenu> getAddViceMap() {
        return addViceMap;
    }

    public void setAddViceMap(Map<String, SubMenu> addViceMap) {
        this.addViceMap = addViceMap;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menuitem_vice_config_itemlayout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final SubMenu subMenu = menuItemList.get(position);
        if (subMenu != null) {
            holder.menuitem_config_vice_name.setText(subMenu.getName());
            if (addViceMap != null && addViceMap.containsKey(subMenu.getId()))
                holder.menuitem_config_vice_op.setImageResource(R.mipmap.modify_selected);
            else
                holder.menuitem_config_vice_op.setImageResource(R.mipmap.modify_unselect);
            final ViewHolder finalHolder = holder;
            holder.menuitem_config_vice_op.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* if (menuItem.isAdd()) {
                        finalHolder.menuitem_config_vice_op.setImageResource(R.mipmap.modify_unselect);
                    } else {
                        finalHolder.menuitem_config_vice_op.setImageResource(R.mipmap.modify_selected);
                    }*/
                    if (addViceMap == null)
                        addViceMap = new HashMap<String, SubMenu>();
                    if (addViceMap.containsKey(subMenu.getId()))
                        addViceMap.remove(subMenu.getId());
                    else
                        addViceMap.put(subMenu.getId(), subMenu);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.menuitem_config_vice_name)
        private TextView menuitem_config_vice_name;
        @ViewInject(R.id.menuitem_config_vice_op)
        private ImageView menuitem_config_vice_op;

        public ViewHolder(View itemView) {
            ViewUtils.inject(this, itemView);
        }
    }
}
