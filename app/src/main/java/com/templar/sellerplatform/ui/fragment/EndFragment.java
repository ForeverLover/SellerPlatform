package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.Order;
import com.templar.sellerplatform.parser.OrderParser;
import com.templar.sellerplatform.ui.adapter.OrderRecyclerAdapter;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.widget.CustomSwipRefreshLayout;
import com.templar.sellerplatform.widget.DividerItemDecoration;
import com.templar.sellerplatform.widget.morerecyclerview.MoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 19:23
 * 描述：结束
 */
public class EndFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MoreRecyclerView.OnLoadMoreListener {
    @ViewInject(R.id.end_swiprefresh_layout)
    private CustomSwipRefreshLayout end_swiprefresh_layout;

    @ViewInject(R.id.end_recyclerview)
    private MoreRecyclerView end_recyclerview;

    private OrderRecyclerAdapter orderRecyclerAdapter;

    private List<Order> orderList;

    private boolean showLoading;
    private boolean isAdd;
    private int uid;
    private int pageindex;
    private int pagesize;

    @Override
    public void initData() {
        super.initData();
        pageindex=1;
        orderRecyclerAdapter = new OrderRecyclerAdapter(new ArrayList<Order>(),getActivity());
    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_end;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {
    }

    @Override
    public void onStart() {
        super.onStart();
        MLog.v("Tag", "onStart");
        setData();
    }

    private void setData(){
        orderList = OrderParser.getInstance().parseOrderList(3);
        if (orderList == null) {
            orderList = new ArrayList<Order>();
        }
        if (pageindex == 1) {
            orderRecyclerAdapter.setList(orderList);
        } else {
            orderRecyclerAdapter.addList(orderList);
        }

        MLog.v("Tag", "size:" + orderRecyclerAdapter.getList().size());
    }

    @Override
    public void initView() {
        super.initView();

        end_swiprefresh_layout.setColorSchemeColors(new int[]{R.color.txt_orange});
        end_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        end_recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL, R.drawable.shape_divideline_order));

        end_recyclerview.setAdapter(orderRecyclerAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        end_swiprefresh_layout.setOnRefreshListener(this);
        end_recyclerview.setOnLoadMoreListener(this);

    }

    @Override
    public void onRefresh() {
        isAdd = false;
        pageindex = 1;
        setData();
        end_swiprefresh_layout.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        MLog.v("Tag", "onLoadMore");
//        showLoading = false;
//        isAdd = true;
//        pageindex++;
//        setData();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                end_recyclerview.stopLoadMore();
            }
        };
        handler.sendEmptyMessageDelayed(1,2*1000);
    }
}
