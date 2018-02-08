package proj.zs.com.hshs.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.adapter.RecyclerViewAdapter;
import proj.zs.com.hshs.base.BaseApplication;
import proj.zs.com.hshs.base.BaseFragment;


/**
 * Created by zengshi on 2018/1/29.
 * 分类Fragment
 */

public class Fication_Fragment extends BaseFragment {
    private ImageView popup_mbtn;
    private List<String> mList;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount=1;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected int layoutId() {
        return R.layout.fication_fragment;

    }

    @Override
    protected void initData() {
        popup_mbtn = view.findViewById(R.id.Menu_Mesage);//获取弹出按钮控件
        mPullLoadMoreRecyclerView=view.findViewById(R.id.fication_pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setGridLayout(2);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        getData();

    }
    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdapter.addAllData(setList());
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, 1000);
    }
    private List<String> setList() {
        List<String> dataList = new ArrayList<>();
        int start = 20 * (mCount - 1);
        for (int i = start; i < 20 * mCount; i++) {
            dataList.add("Second" + i);
        }
        return dataList;

    }
    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
            getData();
        }

        @Override
        public void onLoadMore() {
            mCount = mCount + 1;
            getData();
        }


        private void setRefresh() {
            mRecyclerViewAdapter.clearData();
            mCount = 1;

        }
    }
    @Override
    protected void initListener() {
        //点击悬浮按钮回到顶部
        popup_mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopuMenu(popup_mbtn);
            }
        });

    }
    private void showPopuMenu(View view) {
        //View当前PopuMenu显示的相对view的位置
        PopupMenu popupMenu = new PopupMenu(BaseApplication.getInstance().context, view);
        //menu布局
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
        //popuMenu的iten点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Toast.makeText(BaseApplication.getInstance().context, item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(BaseApplication.getInstance().context, "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
    @Override
    protected void loadData() {

    }

}
