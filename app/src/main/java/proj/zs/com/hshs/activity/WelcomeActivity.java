package proj.zs.com.hshs.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseApplication;
import proj.zs.com.hshs.model.bean.LoginCheckBean;
import proj.zs.com.hshs.prcenter.AdPresenterImpl;
import proj.zs.com.hshs.utils.L;
import proj.zs.com.hshs.utils.NetUtils;
import proj.zs.com.hshs.utils.SPUtils;

/**
 * Created by zengshi on 2018/1/31.
 * 欢迎页Activity
 */

public class WelcomeActivity extends Activity implements AdContract.View {
    int timeCount = 0;
    private ImageView welcome;
    private LinearLayout layoutSkip;
    private LoginCheckBean loginCheckBean;
    boolean continueCount = true;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countNum();
            if (continueCount) {
                handler.sendMessageDelayed(handler.obtainMessage(-1), 1000);
            }
        }
    };
    private Bitmap bitmap;
    private AdPresenterImpl pAd;
    private int initTimeCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_acticvity);
        welcome=findViewById(R.id.welcome);
        layoutSkip=findViewById(R.id.layout_skip);
        layoutSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueCount=false;
                toNextActivity();
                finish();
            }
        });
        pAd = new AdPresenterImpl();
        pAd.attachView((AdContract.View) this);
        initTimeCount = 6;

        loginCheckBean = new LoginCheckBean();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (NetUtils.isConnected(WelcomeActivity.this)) {
            pAd.getLoginCheck();
        }


//        layoutSkip.setVisibility(View.INVISIBLE);
        handler.sendMessageDelayed(handler.obtainMessage(-1), 1000);

    }

//    @OnClick({R.id.welcome, R.id.tv_second, R.id.layout_skip})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.welcome:
//                String url= (String) SPUtils.get(this,"asUrl",null);
//                if (!url.equals("null")){
//                    continueCount=false;
//                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
//                    intent.putExtra("title","震惊");
//                    intent.putExtra("url",url);
//                    intent.putExtra("from","advertising");
//                    startActivity(intent);
//                    finish();
//                }
//                break;
//            case R.id.tv_second:
//                break;
//            case R.id.layout_skip:
//                continueCount=false;
//                toNextActivity();
//                finish();
//                break;
//        }
//    }
    private int countNum(){//数秒
        timeCount++;
        if (timeCount==3){//超过2秒如果没有网络，则进入下一个界面
            if(!NetUtils.isConnected(WelcomeActivity.this)){
                continueCount=false;
                toNextActivity();
                finish();
            }
            if (!loginCheckBean.isPlayAd()){//如果服务端不允许播放广告，就直接进入下一个界面
                continueCount=false;
                toNextActivity();
                finish();
            }else {
                welcome.setVisibility(View.VISIBLE);
                layoutSkip.setVisibility(View.VISIBLE);

            }
        }
        if (timeCount==initTimeCount){
            continueCount=false;
            toNextActivity();
            finish();
        }
        return timeCount;
    }
    public void toNextActivity() {//根据是否保存有 token 判断去登录界面还是主界面
        L.d("到下一个界面");
        Intent intent = null;
        String token = (String) SPUtils.get(WelcomeActivity.this, "token", "");
        if (TextUtils.isEmpty(token)) {
            intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        } else {
            intent = new Intent(WelcomeActivity.this, MainActivity.class);
            BaseApplication.setToken(token);
        }
        startActivity(intent);
        finish();
    }


    @Override
    public void showToast(String message) {

    }

    @Override
    public void showAlertDialog(String title, String message) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showProgressDialog(String message) {

    }

    @Override
    public void setAdTime(int count) {
        initTimeCount = count + 3;
    }

    @Override
    public void setLoginCheckBean(LoginCheckBean loginCheckBean) {
        this.loginCheckBean = loginCheckBean;
    }

    @Override
    public void setAdImg(Bitmap bitmap) {
        if (bitmap != null) {
            welcome.setImageBitmap(bitmap);
        } else {//加强用户体验，如果是获取到的bitmap为null，则直接跳过
            continueCount = false;
            toNextActivity();
            finish();
        }
    }
}
