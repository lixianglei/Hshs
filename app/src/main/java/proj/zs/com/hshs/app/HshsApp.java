package proj.zs.com.hshs.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zengshi on 2018/1/29.
 */

public class HshsApp extends Application {
    public static HshsApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {
        //初始化Leak内存泄露检测工具
        LeakCanary.install(this);
        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    public static HshsApp getInstance() {
        return mInstance;
    }

}
