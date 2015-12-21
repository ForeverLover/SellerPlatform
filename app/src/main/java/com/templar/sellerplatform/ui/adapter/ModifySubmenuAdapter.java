package com.templar.sellerplatform.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
 * 创建时间：2015/12/21 15:33
 * 描述：$TODO
 */
public class ModifySubmenuAdapter extends BaseAdapter{
    private List<SubMenu> subMenuList;
    private Context mContext;

    public ModifySubmenuAdapter(List<SubMenu> subMenuList, Context mContext) {
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.modify_menu_item_layout,null);
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
        }

        return convertView;
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
