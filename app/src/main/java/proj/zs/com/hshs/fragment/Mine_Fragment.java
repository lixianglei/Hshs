package proj.zs.com.hshs.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.support.design.widget.AppBarLayout;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.adapter.RecyclerViewAdapter;
import proj.zs.com.hshs.base.BaseApplication;
import proj.zs.com.hshs.base.BaseFragment;
import proj.zs.com.hshs.contase.MyScrollView;


/**
 * Created by zengshi on 2018/1/29.
 * 个人中心Fragment
 */

public class Mine_Fragment extends BaseFragment {
    private Mine_WorkFragment mine_workFragment;
    private Mine_MyShareFragment mine_myShareFragment;
    private Mine_FollowFragment mine_followFragment;
    private MyScrollView scrollView;
    private View headerView;
    private ImageView imgView;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount=1;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    @Override
    protected int layoutId() {
        return R.layout.me_fragment_layout;
    }

    @Override
    protected void initData() {
//        TableLayout tableLayout=view.findViewById(R.id.tabs);
        ViewPager viewPager=view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        //设置viewpager数据
//        tableLayout.setupWithViewPager(viewPager);
//        tableLayout.setTabMode(TableLayout.MODE_FIXED);

        mPullLoadMoreRecyclerView=view.findViewById(R.id.me_pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setGridLayout(2);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        getData();
//        scrollView=view.findViewById(R.id.my_Scro);
        headerView=view.findViewById(R.id.lay_header);
//        imgView=view.findViewById(R.id.civ_avatar);
        headerView.post(new Runnable() {
            @Override
            public void run() {
//                scrollView.setHeaderView(headerView,imgView);
            }
        });

    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmenetList=new ArrayList<>();
        private final List<String> mFragmentTitleList=new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmenetList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmenetList.size();
        }
        public void addFrag(Fragment fragment,String title){
            mFragmenetList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        mine_workFragment=new Mine_WorkFragment();
        Bundle data=new Bundle();
        data.putInt("id",0);
        mine_workFragment.setArguments(data);
//        adapter.addFrag(mine_workFragment,"作品集");

        mine_myShareFragment=new Mine_MyShareFragment();
        data.putInt("id",1);
        mine_myShareFragment.setArguments(data);
//        adapter.addFrag(mine_myShareFragment,"我的分享");

        mine_followFragment=new Mine_FollowFragment();
        data.putInt("id",2);
        mine_followFragment.setArguments(data);
//        adapter.addFrag(mine_followFragment,"关注的用户");

//        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

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

    }

    @Override
    protected void loadData() {

    }

}
