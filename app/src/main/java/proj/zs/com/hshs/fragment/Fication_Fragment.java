package proj.zs.com.hshs.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.adapter.RecyclerViewAdapter;
import proj.zs.com.hshs.adapter.ViewPagerAdapter;
import proj.zs.com.hshs.base.BaseApplication;
import proj.zs.com.hshs.base.BaseFragment;

import static proj.zs.com.hshs.utils.DensityUtil.dip2px;


/**
 * Created by zengshi on 2018/1/29.
 * 分类Fragment
 */

public class Fication_Fragment extends BaseFragment {
    private ViewPagerAdapter myPagerAdapter;
    private ImageView popup_mbtn;
    private List<String> mList;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount=1;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private TabLayout fictoolbarTab;
    private ViewPager viewPager;
    private TabLayout toolbarTab;
    @Override
    protected int layoutId() {
        return R.layout.fication_fragment;

    }

    @Override
    protected void initData() {
        toolbarTab=view.findViewById(R.id.fictoolbar_tab);
        viewPager=view.findViewById(R.id.ficmain_vp_container);
        popup_mbtn = view.findViewById(R.id.Menu_Mesage);//获取弹出按钮控件
        mPullLoadMoreRecyclerView=view.findViewById(R.id.fication_pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setGridLayout(2);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        getData();
        //给viewpager设置适配器
        setViewPagerAdapter();
        //tablayout和viewpager建立联系
        setTabBindViewPager();

    }

    private void setTabBindViewPager() {
        //tablayout和viewpager建立联系方式一：tab与viewpager之间的相互绑定
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (toolbarTab));
        toolbarTab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (viewPager));
        toolbarTab.post(new Runnable() {
            @Override
            public void run() {
                reflex(toolbarTab);
            }
        });
        //tablayout和viewpager建立联系方式二： 使用此方法Tablayout中的TabItem设置icon无效
        // （android:icon="@drawable/tab_selector" ）只能使用 android:text="分享"
        //  toolbarTab.setupWithViewPager(mViewPager);
    }

    private void setViewPagerAdapter() {
        myPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager(),getContext());
        viewPager.setAdapter(myPagerAdapter);
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
    public void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(tabLayout.getContext(), 10);
                    int dp20 = dip2px(tabLayout.getContext(), 30);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp20;
                        params.rightMargin = dp20;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
