package com.templar.sellerplatform.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.templar.sellerplatform.R;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/19 17:11
 * 描述：${todo}
 */
public class TestRecyclerAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    private List<String> stringList;

    public TestRecyclerAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pro_item_layout,
                    parent, false);
            return new ViewHolder(v);
        }
        // type == TYPE_FOOTER 返回footerView
        else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.custom_loadmore_footer, null);

            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position<stringList.size()) {
                String str = stringList.get(position);
                ViewHolder h = (ViewHolder) holder;
            }

    }

    @Override
    public int getItemCount() {
        return stringList.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView order_item_product_name;

        public ViewHolder(View itemView) {
            super(itemView);
            order_item_product_name = (TextView) itemView.findViewById(R.id.order_item_product_name);
        }
    }

    class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }


    }
}
