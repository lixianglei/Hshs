package proj.zs.com.hshs.fragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jaeger.library.StatusBarUtil;

import java.lang.reflect.Field;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import proj.zs.com.hshs.R;
import proj.zs.com.hshs.adapter.ViewPagerAdapter;
import proj.zs.com.hshs.base.BaseApplication;
import proj.zs.com.hshs.base.BaseFragment;

import static proj.zs.com.hshs.utils.DensityUtil.dip2px;

/**
 * Created by zsz on 2018/2/8.
 */

public class Me_Fragment extends BaseFragment {
    private Toolbar toolbar;
    private ImageView headIv;
    private LinearLayout headLayout;
    private TabLayout toolbarTab;
    AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager mViewPager;
    private NestedScrollView nsv;
    private CoordinatorLayout coordinatorLayout;
    private ViewPagerAdapter myPagerAdapter;
    @Override
    protected int layoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initData() {
        toolbar=view.findViewById(R.id.toolbar);//ScrollView上半部分
        headIv=view.findViewById(R.id.head_iv);//头像
        headLayout=view.findViewById(R.id.head_layout);//CollapsingToolbarLayout内部显示内容部分
        toolbarTab=view.findViewById(R.id.toolbar_tab);//tab分类条目
        appBarLayout=view.findViewById(R.id.app_bar_layout);
        collapsingToolbarLayout=view.findViewById(R.id.collapsingToolbarLayout);
        mViewPager=view.findViewById(R.id.main_vp_container);//ViewPager
        nsv=view.findViewById(R.id.nsv);
        coordinatorLayout=view.findViewById(R.id.coordinator_Layout);//整个布局
        //用toolBar替换ActionBar
        setToolBarReplaceActionBar();
        //吧title设置到CollapsingToolbarLayout上
        setTitleToCollapsingToolbarLayout();
        //给viewpager设置适配器
        setViewPagerAdapter();
        //tablayout和viewpager建立联系
        setTabBindViewPager();
        //设置毛玻璃效果和沉浸式状态栏
//        loadBlurAndSetStatusBar();
        //设置头像
//        Glide.with(BaseApplication.getInstance().context).load(R.drawable.bg)
//                .bitmapTransform(new RoundedCornersTransformation(BaseApplication.getInstance().context,90,90)).into(headIv);

    }
    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        //目的是让状态栏半透明
//         StatusBarUtil.setTranslucent((Activity) BaseApplication.getInstance().context, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        //目的是让状态栏全透明
//        StatusBarUtil.setTransparent((Activity) BaseApplication.getInstance().context);

        Glide.with(this).load(R.drawable.bg).bitmapTransform(new BlurTransformation(BaseApplication.getInstance().context, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        headLayout.setBackground(resource);
                        coordinatorLayout.setBackground(resource);
                    }
                });

        Glide.with(this).load(R.drawable.bg).bitmapTransform(new BlurTransformation(BaseApplication.getInstance().context, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        collapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }
    /**
     * tablayout和viewpager建立联系
     */
    private void setTabBindViewPager() {
        //tablayout和viewpager建立联系方式一：tab与viewpager之间的相互绑定
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (toolbarTab));
        toolbarTab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (mViewPager));
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

    /**
     * 给Viewpager设置适配器
     */

    private void setViewPagerAdapter() {
        myPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager(),getContext());
        mViewPager.setAdapter(myPagerAdapter);
    }
    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private void setTitleToCollapsingToolbarLayout() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset<= -headLayout.getHeight()/2){
                    collapsingToolbarLayout.setTitle(" 个人中心");
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.ground));
                } else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });
    }
//  //用toolBar替换ActionBar
    private void setToolBarReplaceActionBar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaseApplication.getInstance().context, "返回", Toast.LENGTH_LONG).show();
                // onBackPressed();//结束程序
            }
        });
    }


    @Override
    protected void initListener() {

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
