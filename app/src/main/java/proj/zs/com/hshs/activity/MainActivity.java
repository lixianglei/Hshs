package proj.zs.com.hshs.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;
import proj.zs.com.hshs.fragment.Appreciate_Fragment;
import proj.zs.com.hshs.fragment.Fication_Fragment;
import proj.zs.com.hshs.fragment.Home_Fragment;
import proj.zs.com.hshs.fragment.Mine_Fragment;
import proj.zs.com.hshs.fragment.Ranking_Fragment;

public class MainActivity extends BaseActivity {
    private RadioButton mine_btn,ficetion_btn,appreciate_btn,home_btn,ranking_bth;
    private PDFView pdfView;
    private static boolean isExit = false;
    private final int BACK = 0;

    private Appreciate_Fragment appreciate_fragment;
    private Fication_Fragment fication_fragment;
    private Home_Fragment home_fragment;
    private Mine_Fragment mine_fragment;
    private Ranking_Fragment ranking_fragment;


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
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        home_btn=findViewById(R.id.Home_Btn_News);
        ficetion_btn=findViewById(R.id.Fiction_Btn);
        ranking_bth=findViewById(R.id.Rankind_Btn);
        appreciate_btn=findViewById(R.id.Appreciate_Btn);
        mine_btn=findViewById(R.id.Mine_Btn);
        select(0);
    }

    private void select(int i) {
        FragmentManager fm=getFragmentManager();//获取Fragment管理器
        FragmentTransaction ft=fm.beginTransaction();//开启一个事物
        hidtFragment(ft);//先隐藏fragment
    }

    private void hidtFragment(FragmentTransaction ft) {
    }

    @Override
    protected void initListener() {
        home_btn.setOnClickListener((View.OnClickListener) this);
        ficetion_btn.setOnClickListener((View.OnClickListener) this);
        ranking_bth.setOnClickListener((View.OnClickListener) this);
        appreciate_btn.setOnClickListener((View.OnClickListener) this);
        mine_btn.setOnClickListener((View.OnClickListener) this);

        mine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PersonActivity.class);
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
            Toast.makeText(MainActivity.this, "再次点击退出绘声绘色",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(BACK, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
