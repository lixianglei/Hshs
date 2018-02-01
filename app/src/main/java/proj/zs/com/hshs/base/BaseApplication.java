package proj.zs.com.hshs.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by zsz on 2018/1/29.
 */

public class BaseApplication extends Application {

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        BaseApplication.token = token;
    }

    private static String token = "";

    /**
     * 维护一个全局的context对象
     */
    public Context context;

    /**
     * 用于存放当前用户（如果有的话）
     */
//    private static UserInfo currentUser;

    //单例模式
    private static BaseApplication myApplication = null;

    public static BaseApplication getInstance() {
        return myApplication;
    }



    /**
     * 定义一个标记
     */
    private static String TAG;

    @Override
    public void onCreate() {
        super.onCreate();
        //把TAG定义为当前类的类名
        TAG = this.getClass().getSimpleName();
        //由于Application类本身已经单例，所以直接按以下处理即可。
        myApplication = this;
        context = getApplicationContext();

        //全局异常处理
//        if(SyncStateContract.Constants.isCollectException){
//            CrashHandlerUtils crashHandler = CrashHandlerUtils.getInstance();
//            crashHandler.init(getApplicationContext());
//        }
    }
}
