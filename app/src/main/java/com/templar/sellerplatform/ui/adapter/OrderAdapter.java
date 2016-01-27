package com.templar.sellerplatform.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.entity.Order;
import com.templar.sellerplatform.utils.StringUtils;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 17:32
 * 描述:
 */
public class OrderAdapter extends BaseAdapter{
    private Context mContext;
    private List<Order> orderList;
    private LayoutInflater mInflater;
    private OrderProductAdapter adapter;
    public OrderAdapter(Context mContext, List<Order> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Order getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.order_item_layout,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
            holder.order_item_commonOp.setVisibility(View.GONE);
            holder.order_item_specialOp.setVisibility(View.GONE);
        }
        final Order order = getItem(position);
        if (order != null) {
            holder.order_addr_tv.setText(order.getOrderAddr());
            holder.order_remark_tv.setText(order.getOrderRemark());
            holder.order_item_orderNo.setText(order.getOrderNo());
            holder.order_item_type.setText(order.getOrderType());
            holder.order_item_buyerTime.setText(order.getBuyerName() + " - " + StringUtils.getTweenTime(order.getOrderTime(), mContext));
            holder.order_item_totalPrice.setText(order.getOrderPrice() + mContext.getString(R.string.unit_yuan_text));
            if (order.getProductList() != null && !order.getProductList().isEmpty()) {
                adapter = new OrderProductAdapter(order.getProductList(), mContext);
                holder.order_product_lv.setAdapter(adapter);
            }
            int currentState = order.getState();
            switch (currentState) {
                case 0:
                    holder.order_item_commonOp.setText(mContext.getString(R.string.order_state_accept));
                    holder.order_item_commonOp.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    holder.order_item_specialOp.setText(mContext.getString(R.string.order_state_deal));
                    holder.order_item_specialOp.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    holder.order_item_specialOp.setText(mContext.getString(R.string.order_state_take));
                    holder.order_item_specialOp.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    holder.order_item_commonOp.setText(mContext.getString(R.string.order_state_unfinish));
                    holder.order_item_commonOp.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.order_item_commonOp.setBackgroundResource(R.drawable.btn_gray_bg);
                    holder.order_item_commonOp.setVisibility(View.VISIBLE);

                    holder.order_item_decline.setText(mContext.getString(R.string.order_decline_prefix).concat(order.getRemainingTime()).concat(mContext.getString(R.string.order_decline_suffix_minute)));
                    holder.order_item_decline.setVisibility(View.VISIBLE);
                    holder.order_item_specialOp.setText(mContext.getString(R.string.order_state_finished));
                    holder.order_item_specialOp.setTextColor(Color.parseColor("#cccccc"));
                    holder.order_item_specialOp.setBackgroundResource(R.drawable.solid_rounded_box_gray);
                    holder.order_item_specialOp.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    holder.order_item_commonOp.setText(mContext.getString(R.string.order_state_unfinish));
                    holder.order_item_commonOp.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.order_item_commonOp.setBackgroundResource(R.drawable.btn_light_gray_bg);
                    holder.order_item_commonOp.setVisibility(View.VISIBLE);
                    holder.order_item_specialOp.setText(mContext.getString(R.string.order_state_finished));
                    holder.order_item_specialOp.setVisibility(View.VISIBLE);
                    holder.order_item_decline.setText(mContext.getString(R.string.order_decline_prefix).concat(order.getRemainingTime()).concat(mContext.getString(R.string.order_decline_suffix_minute)));
                    holder.order_item_decline.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    holder.order_item_commonOp.setText(mContext.getString(R.string.order_state_finished));
                    holder.order_item_commonOp.setTextColor(Color.parseColor("#fdfdfd"));
                    holder.order_item_commonOp.setBackgroundResource(R.drawable.btn_light_gray_bg);
                    holder.order_item_commonOp.setVisibility(View.VISIBLE);
                    break;
            }
            holder.order_item_commonOp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.order_item_specialOp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return convertView;
    }

    class ViewHolder{
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
        private TextView order_item_decline;
        @ViewInject(R.id.order_item_commonOp)
        private TextView order_item_commonOp;
        @ViewInject(R.id.order_item_specialOp)
        private TextView order_item_specialOp;
        @ViewInject(R.id.order_product_lv)
        private CustomListView order_product_lv;

        public ViewHolder(View itemView) {
            ViewUtils.inject(this, itemView);
        }
    }
}
