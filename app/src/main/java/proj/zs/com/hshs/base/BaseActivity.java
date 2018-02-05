package proj.zs.com.hshs.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

import proj.zs.com.hshs.activity.App;

/**
 * Created by zengshi on 2017/4/11.
 */

public abstract class BaseActivity extends FragmentActivity {
    protected FragmentManager fragmentManager;

    public static boolean islogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.activity = this;
        setContentView(layoutId());
//        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        initData();
        initListener();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        App.activity = this;
    }

    /**
     * @return返回View对象
     */
    protected abstract int layoutId();

    /**
     * 初始化对象
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();


    /**
     * 加载数据
     */
    protected abstract void loadData();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
