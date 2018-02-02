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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;
import proj.zs.com.hshs.model.bean.foundPassWord;
import proj.zs.com.hshs.model.bean.loginMsg;

import static com.facebook.stetho.inspector.network.PrettyPrinterDisplayType.JSON;


/**
 * Created by zengshi on 2018/1/31.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_bg)
    ImageView loginBg;
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_title)
    ImageView loginTitle;
    @BindView(R.id.Login_Name)
    EditText account;
    @BindView(R.id.login_user)
    LinearLayout loginUser;
    @BindView(R.id.Login_pwd)
    EditText passworld;
    @BindView(R.id.login_pwd)
    LinearLayout loginPwd;
    @BindView(R.id.login_save_pwd)
    CheckBox loginSavePwd;
    @BindView(R.id.Login_Btn)
    Button LoginBtn;

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

    }

    @Override
    protected void initListener() {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Login_Name, R.id.login_user, R.id.Login_pwd, R.id.login_save_pwd, R.id.Login_Btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_Name:
                break;
            case R.id.login_user:
                break;
            case R.id.Login_pwd:
                break;
            case R.id.login_save_pwd:
                break;
            case R.id.Login_Btn:
               login();
                break;
        }
    }
    /**
     * 登录方法
     */
    @SuppressLint("HandlerLeak")
    public boolean login() {
        if (isUserNameAndPwdValid()) {
            mHandler=new Handler(){
                public void handleMessage(Message msg){
                    switch(msg.what){
                        case -1:
                            Toast.makeText(LoginActivity.this,"服务器连接失败!",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(LoginActivity.this,"出错啦...",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            String temp=(String)msg.obj;
                            //将拿到的json转换成数组
//                            List<loginMsg> loginMsgInfo=JSON.parseArray(temp, loginMsg.class);
//                            List<foundPassWord> foundPassWordInfo=JSON.parseArray(temp,foundPassWord.class);
//                            String userName=account.getText().toString().trim();
//                            String pwd=passworld.getText().toString().trim();
//                            String AccountNum=loginMsgInfo.get(0).getAccountNum();
//                            String psaaword=foundPassWordInfo.get(0).getPassWord();
//                            if (account.equals(AccountNum)&& pwd.equals(psaaword)) {
//                                //实现界面的跳转
//                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(intent);
//                                //关闭当前界面
//                                finish();
//                            }else{
                                //实现界面的跳转
//                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(intent);
                                //关闭当前界面
                                finish();
//       Toast.makeText(LoginActivity.this, "用户名或密码错误", 0).show();
                            }
                    }
//                }
            };
            //主线程
            new Thread(){
                public void run(){
                    Message msg= new Message();
                    try{
                        Map<String,String> parmas=new HashMap<String,String>();
                        parmas.put("username","1");
                        parmas.put("password","2");
                        String loginMsgurl="http://192.168.1.110:8080/SchoolShopJson/LoginMsg.txt";
                        String foundPassWordurl="http://192.168.1.110:8080/SchoolShopJson/foundPassWord.txt";
                        //要发送的数据和访问的地址
//                        String resultloginMsgString=AgentApi.dopost(parmas,loginMsgurl);
//                        String resultfpasswordString=AgentApi.dopost(parmas, foundPassWordurl);
                        //发送handler信息
                        msg.what=1;
//                        msg.obj=resultloginMsgString;
                    }catch(Exception e){
                        e.printStackTrace();
                        //使用-1代表程序异常
                        msg.what=-2;
                        msg.obj=e;
                    }
                    mHandler.sendMessage(msg);
                }
            }.start();
        }
        return false;
    }
    /**
     * 判断用户名和密码是否有效
     *
     * @return
     */
    public boolean isUserNameAndPwdValid() {
        // 用户名和密码不得为空
        if (account.getText().toString().trim().equals("")) {
            Toast.makeText(this,"用户名和密码不得为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (passworld.getText().toString().trim().equals("")) {
            Toast.makeText(this, "用户名和密码不得为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }    }

