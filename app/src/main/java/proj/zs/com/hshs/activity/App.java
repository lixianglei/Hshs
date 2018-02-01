package proj.zs.com.hshs.activity;

import android.app.Application;

import proj.zs.com.hshs.base.BaseActivity;
import proj.zs.com.hshs.base.BaseFragment;


/**
 * Created by zengshi on 2017/4/5.
 */

public class App extends Application {

    public static BaseActivity activity;
    public static BaseFragment lastFragment;
    public static BaseFragment softwarefragment;

}
