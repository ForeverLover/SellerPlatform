package com.templar.sellerplatform.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.templar.sellerplatform.R;
import com.templar.sellerplatform.entity.OrderProduct;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/19 14:37
 * 描述：${todo}
 */
public class OrderProductAdapter extends BaseAdapter{
    private List<OrderProduct> orderProductList;
    private Context mContext;

    public OrderProductAdapter(List<OrderProduct> orderProductList, Context mContext) {
        this.orderProductList = orderProductList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return orderProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.order_pro_item_layout,null);
            holder.order_item_product_amount= (TextView) convertView.findViewById(R.id.order_item_product_amount);
            holder.order_item_product_name= (TextView) convertView.findViewById(R.id.order_item_product_name);
            holder.order_item_proInfo= (TextView) convertView.findViewById(R.id.order_item_proInfo);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        OrderProduct orderProduct=orderProductList.get(position);
        if (orderProduct!=null){
            holder.order_item_product_amount.setText("X"+orderProduct.getAmount());
            holder.order_item_product_name.setText(orderProduct.getProductName()+" "+orderProduct.getProductType());
            holder.order_item_proInfo.setText(orderProduct.getProductDescribe());
        }
        return convertView;
    }

    class ViewHolder{
        private TextView order_item_product_amount;
        private TextView order_item_product_name;
        private TextView order_item_proInfo;
    }
}
