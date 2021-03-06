package proj.zs.com.hshs.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zengshi on 2018/1/31.
 * 登录Activity
 */

public class LoginActivity extends BaseActivity {
    private TextView login;
    private EditText uname=null;
    private EditText upswd=null;
    private CheckBox checkBoxButton=null;

    //声明变量
    private Handler handler;
    private SharedPreferences sp;
    private SharedPreferences.Editor mEditor;
    private ProgressDialog progressDialog;
    private static boolean isExit = false;
    private final int FAILURE = 0;
    private final int BACK = 0;
    private final int SUCCESS = 1;

    @Override
    protected int layoutId() {
        return R.layout.login_activity;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initData() {
      sp=this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
      login=findViewById(R.id.Login_Btn);
      checkBoxButton=findViewById(R.id.login_save_pwd);
      upswd=findViewById(R.id.Login_pwd);
      uname=findViewById(R.id.Login_Name);
      if (sp.getBoolean("checkboxBoolean",false)){
          uname.setText(sp.getString("uname",null));
          upswd.setText(sp.getString("upswd",null));
          checkBoxButton.setChecked(true);
      }
    }

    @Override
    protected void initListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                if (v==login){
                    String name=uname.getText().toString();
                    String pswd=upswd.getText().toString();
                    if (name.trim().equals("")){
                        Toast.makeText(LoginActivity.this, "请您输入用户名！", Toast.LENGTH_SHORT).show();
                        return;
                     }
                     if (pswd.trim().equals("")){
                         Toast.makeText(LoginActivity.this, "请您输入密码！", Toast.LENGTH_SHORT).show();
                         return;
                      }
                      boolean CheckBoxLogin=checkBoxButton.isChecked();
                     //记住密码
                    if (CheckBoxLogin){
                        mEditor=sp.edit();
                        mEditor.putString("uname",name);
                        mEditor.putString("upswd",pswd);
                        mEditor.putBoolean("checkboxBoolean",true);
                        mEditor.commit();
                    }
                    else {
                        mEditor=sp.edit();
                        mEditor.putString("uname",null);
                        mEditor.putString("upswd",null);
                        mEditor.putBoolean("checkboxBoolean",false);
                        mEditor.commit();
                    }
                    //Intent跳转
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);

                    startActivity(intent);
                }
        }
    });
    }

    @Override
    protected void loadData() {

    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case FAILURE:
//                    progressDialog.dismiss();
//                    mEditor.putBoolean(SPKeyUtils.ISLOGIN,false);
//                    mEditor.commit();
//                    Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }
    };

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

