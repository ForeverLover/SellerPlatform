package com.templar.sellerplatform.widget.morerecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.templar.sellerplatform.utils.MLog;


/**
 * 上拉加载更多RecyclerView
 * Created by jph on 2015/9/6.
 */
public class MoreRecyclerView extends RecyclerView {

    public static final String TAG = "MoreRecyclerView";

    private boolean mLoadable = true;
    private MoreRecyclerAdapter mMoreRecyclerAdapter;
    private OnLoadMoreListener mOnLoadMoreListener;
    private MScrollListener mScrollListener;
    /**
     * 滑动到最下面时的上拉操作
     */

    private int mTouchSlop;
    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;


    public MoreRecyclerView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
    }

    @Override
    public final void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

    public void setAdapter(MoreRecyclerAdapter moreRecyclerAdapter) {
        super.setAdapter(moreRecyclerAdapter);
        this.mMoreRecyclerAdapter = moreRecyclerAdapter;
        if (mScrollListener != null) {
            removeOnScrollListener(mScrollListener);
        }
        mScrollListener = new MScrollListener();
        addOnScrollListener(new MScrollListener());
    }

    private class MScrollListener extends OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
//            if (!(getLayoutManager() instanceof LinearLayoutManager)) {
//                return;
//            }
//            LinearLayoutManager ll = (LinearLayoutManager) getLayoutManager();
//            if (ll.findLastVisibleItemPosition() == mMoreRecyclerAdapter.getRealItemCount() - 1) {
//                MLog.i(TAG, "scrolled last item:"+ll.findLastVisibleItemPosition());
//                startLoadMore();
//
//            }
            if (canLoad()) {
                startLoadMore();
            }
        }
    }


    public void startLoadMore() {
        if (!mLoadable || isLoading) {
            return;
        }
        isLoading = true;
        if (mMoreRecyclerAdapter != null) {
            mMoreRecyclerAdapter.showFooterView();
            MLog.v("Tag","showLoadMore");
        }
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public void stopLoadMore() {
        isLoading = false;
        if (mMoreRecyclerAdapter != null) {
            mMoreRecyclerAdapter.hideFooterView();
        }
    }

    public boolean isLoadable() {
        return mLoadable;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoadable(boolean mLoadable) {
        this.mLoadable = mLoadable;
    }

    public OnLoadMoreListener getOnLoadMoreListener() {
        return mOnLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    /**
     * 加载监听
     */
    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                // 抬起
                if (canLoad()) {
                    startLoadMore();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        MLog.v(TAG,"isBottom:"+isBottom()+" isLoading:"+!isLoading()+" isPullUp"+isPullUp());
        return isBottom() && !isLoading && isPullUp();
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {
        if (!(getLayoutManager() instanceof LinearLayoutManager)) {
            return false;
        }
        LinearLayoutManager ll = (LinearLayoutManager) getLayoutManager();
       /* if (ll.findLastVisibleItemPosition() == mMoreRecyclerAdapter.getRealItemCount() - 1) {
            MLog.i(TAG, "scrolled last item:" + ll.findLastVisibleItemPosition());
            return true;

        }*/
        if(ll.findViewByPosition(ll.findFirstVisibleItemPosition()).getTop()==0 && ll.findFirstVisibleItemPosition()==0) return true;
        return false;
    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }
}
