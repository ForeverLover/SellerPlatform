package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.Order;
import com.templar.sellerplatform.parser.OrderParser;
import com.templar.sellerplatform.ui.adapter.OrderAdapter;
import com.templar.sellerplatform.widget.CustomListView;

import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 17:32
 * 描述：$TODO
 */
public class OrderListFragment extends BaseFragment {
    @ViewInject(R.id.order_lv)
    private CustomListView order_lv;
    @ViewInject(R.id.order_scroll_layout)
    private ScrollView order_scroll_layout;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    private int type;

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_order_lv;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {
    }

    @Override
    public void initData() {
        super.initData();
        order_scroll_layout.setFocusable(false);
        if (getArguments() != null) {
            type = getArguments().getInt("type", -1);
        }
        orderList = OrderParser.getInstance().parseOrderList(type);
        if (orderList != null) {
            orderAdapter = new OrderAdapter(getActivity(), orderList);
            order_lv.setAdapter(orderAdapter);
        }
    }
}
