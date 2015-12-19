package com.templar.sellerplatform.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.templar.sellerplatform.R;
import com.templar.sellerplatform.config.BaseFragment;
import com.templar.sellerplatform.entity.Order;
import com.templar.sellerplatform.parser.OrderParser;
import com.templar.sellerplatform.ui.adapter.TestRecyclerAdapter;
import com.templar.sellerplatform.widget.CustomSwipRefreshLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 19:23
 * 描述：处理中
 */
public class DealFragment extends BaseFragment {
    @ViewInject(R.id.deal_swiprefresh_layout)
    private CustomSwipRefreshLayout deal_swiprefresh_layout;

    @ViewInject(R.id.deal_list)
    private RecyclerView deal_list;

    private List<Order> orderList;
    private TestRecyclerAdapter recyclerAdapter;


    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_deal;
    }

    @Override
    protected void onCreateView(View contentView, Bundle savedInstanceState, LayoutInflater inflater) {

    }

    @Override
    public void initView() {
        super.initView();
        // 模拟一些数据
        final List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("item - " + i);
        }

        // 构造适配器
        final BaseAdapter adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                datas);
        orderList = OrderParser.getInstance().parseOrderList();
        recyclerAdapter=new TestRecyclerAdapter(datas);


        deal_list.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));

        deal_list.setAdapter(recyclerAdapter);


        // 加载监听器
        deal_swiprefresh_layout.setOnLoadListener(new CustomSwipRefreshLayout.OnLoadListener() {

            @Override
            public void onLoad() {

                Toast.makeText(getActivity(), "load", Toast.LENGTH_SHORT).show();

                deal_list.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        datas.add(new Date().toGMTString());
                        adapter.notifyDataSetChanged();
                        // 加载完后调用该方法
                        deal_swiprefresh_layout.setLoading(false);
                    }
                }, 1500);

            }
        });

    }
}
