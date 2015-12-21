package com.templar.sellerplatform.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.entity.SubMenu;
import com.templar.sellerplatform.ui.adapter.base.BaseRecyclerAdapter;
import com.templar.sellerplatform.utils.MLog;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/21 13:26
 * 描述：$TODO
 */
public class ToggleSubmenuRecyclerAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder, SubMenu> {

    public ToggleSubmenuRecyclerAdapter(List<SubMenu> listData) {
        super(listData);
        if (listData != null) MLog.v("Tag", "list'size==" + listData.size());
        else MLog.v("Tag", "list is null");
    }

    @Override
    public RecyclerView.ViewHolder onRealCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.toggle_sublist_item_layout,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onRealBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onRealBindViewHolder(viewHolder, position);
        final SubMenu subMenu = getItem(position);
        if (subMenu != null) {
            final ViewHolder holder = (ViewHolder) viewHolder;
            holder.toggle_sublist_item_cb.setChecked(subMenu.isOn());
            holder.toggle_sublist_item_name.setText(subMenu.getName());
            holder.toggle_sublist_item_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    subMenu.setOn(isChecked);
//                    holder.toggle_sublist_item_cb.setChecked(isChecked);
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @ViewInject(R.id.toggle_sublist_item_name)
        private TextView toggle_sublist_item_name;
        @ViewInject(R.id.toggle_sublist_item_cb)
        private CheckBox toggle_sublist_item_cb;

        public ViewHolder(View itemView) {
            super(itemView);
            ViewUtils.inject(this, itemView);
        }


    }
}
