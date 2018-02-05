package proj.zs.com.hshs.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zengshi on 2018/1/31.
 * 登录Activity
 */

public class LoginActivity extends BaseActivity {
    private Button login_btn;
    //声明变量
    private Handler handler;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ProgressDialog progressDialog;
    private static boolean isExit = false;
    private final int FAILURE = 0;
    private final int BACK = 0;
    private final int SUCCESS = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                isExit = false;
            }
        }
    };


    @Override
    protected int layoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initData() {
      login_btn=findViewById(R.id.Login_Btn);
    }

    @Override
    protected void initListener() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginActivity.this,ModifyPWD_Activity.class);
            startActivity(intent);
            finish();
        }
    });

    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(LoginActivity.this, "再次点击退出绘声绘色",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(BACK, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

}

