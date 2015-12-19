package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.Order;
import com.templar.sellerplatform.parser.OrderParser;
import com.templar.sellerplatform.ui.adapter.OrderAdapter;
import com.templar.sellerplatform.utils.MLog;
import com.templar.sellerplatform.widget.CustomSwipRefreshLayout;
import com.templar.sellerplatform.widget.DividerItemDecoration;
import com.templar.sellerplatform.widget.morerecyclerview.MoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 19:21
 * 描述：通知
 */
public class NoticeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MoreRecyclerView.OnLoadMoreListener {
    @ViewInject(R.id.notice_refreshLayout)
    private CustomSwipRefreshLayout notice_refreshLayout;
    @ViewInject(R.id.notice_view)
    private MoreRecyclerView notice_view;

    private OrderAdapter orderAdapter;

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
        orderAdapter = new OrderAdapter(new ArrayList<Order>(),getActivity());
    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_notice;
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
        orderList = OrderParser.getInstance().parseOrderList();
        if (orderList == null) {
            orderList = new ArrayList<Order>();
        }
        if (pageindex == 1) {
            orderAdapter.setList(orderList);
        } else {
            orderAdapter.addList(orderList);
        }

        MLog.v("Tag","size:"+orderAdapter.getList().size());
    }

    @Override
    public void initView() {
        super.initView();
        notice_refreshLayout.setColorSchemeColors(new int[]{R.color.txt_orange});
        notice_view.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        notice_view.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL, R.drawable.shape_divideline_order));
        notice_view.setAdapter(orderAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        notice_refreshLayout.setOnRefreshListener(this);
       notice_view.setOnLoadMoreListener(this);

    }

    @Override
    public void onRefresh() {
        MLog.v("Tag", "onRefresh");
        isAdd = false;
        pageindex = 1;
       setData();
        notice_refreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
//        MLog.v("Tag", "onLoadMore");
//        showLoading = false;
//        isAdd = true;
//        pageindex++;
//        setData();
        notice_view.stopLoadMore();
    }
}
