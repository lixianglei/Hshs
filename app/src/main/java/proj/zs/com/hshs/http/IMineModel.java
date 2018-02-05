package proj.zs.com.hshs.http;


import android.net.ConnectivityManager;

public interface IMineModel {


    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @param callback
     */
    void Login(String username, String pwd, ConnectivityManager.NetworkCallback callback);

    /**
     * 获取登录用户信息
     *
     * @param uid
     * @param callback
     */
    void getInfo(String uid, ConnectivityManager.NetworkCallback callback);




}
