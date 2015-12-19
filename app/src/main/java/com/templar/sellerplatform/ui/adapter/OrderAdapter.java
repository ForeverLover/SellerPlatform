package com.templar.sellerplatform.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.entity.Order;
import com.templar.sellerplatform.ui.adapter.base.BaseRecyclerAdapter;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/19 14:15
 * 描述：
 */
public class OrderAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder, Order> {
    private Context mContext;
    private List<Order> orderList;
    private OrderProductAdapter adapter;

    public OrderAdapter(List<Order> listData, Context mContext) {
        super(listData);
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onRealCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onRealBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onRealBindViewHolder(viewHolder, position);
        final Order order = getItem(position);
        if (order != null) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.order_addr_tv.setText(order.getOrderAddr());
            holder.order_remark_tv.setText(order.getOrderRemark());
            holder.order_item_orderNo.setText(order.getOrderNo());
            holder.order_item_type.setText(order.getOrderType());
            holder.order_item_buyerTime.setText(order.getBuyerName() + " " + order.getOrderTime());
            holder.order_item_totalPrice.setText(order.getOrderPrice() + mContext.getString(R.string.unit_yuan_text));
            if (order.getProductList() != null && !order.getProductList().isEmpty()) {
                adapter = new OrderProductAdapter(order.getProductList(), mContext);
                holder.order_product_lv.setAdapter(adapter);
            }
            holder.order_item_commonOp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @ViewInject(R.id.order_item_orderNo)
        private TextView order_item_orderNo;
        @ViewInject(R.id.order_item_buyerTime)
        private TextView order_item_buyerTime;
        @ViewInject(R.id.order_item_type)
        private TextView order_item_type;
        @ViewInject(R.id.order_addr_tv)
        private TextView order_addr_tv;
        @ViewInject(R.id.order_remark_tv)
        private TextView order_remark_tv;
        @ViewInject(R.id.order_item_totalPrice)
        private TextView order_item_totalPrice;
        @ViewInject(R.id.order_item_decline)
        private TextView order_item_decline; @ViewInject(R.id.order_item_commonOp)
        private TextView order_item_commonOp;
        @ViewInject(R.id.order_product_lv)
        private CustomListView order_product_lv;

        public ViewHolder(View itemView) {
            super(itemView);
            ViewUtils.inject(this, itemView);
        }


    }


}
