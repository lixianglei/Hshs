package proj.zs.com.hshs.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zsz on 2018/1/31.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_bg)
    ImageView loginBg;
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_title)
    ImageView loginTitle;
    @BindView(R.id.login_user)
    LinearLayout loginUser;
    @BindView(R.id.login_pwd)
    LinearLayout loginPwd;
    @BindView(R.id.login_save_pwd)
    CheckBox loginSavePwd;

    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;

    private ProgressDialog progressDialog;

    private final int FAILURE = 0;

    private final int SUCCESS = 1;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_bg, R.id.login_user, R.id.login_pwd, R.id.login_save_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_bg:
                break;
            case R.id.login_user:
                break;
            case R.id.login_pwd:
                break;
            case R.id.login_save_pwd:
                break;
        }
    }
}
