package proj.zs.com.hshs.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;
import proj.zs.com.hshs.fragment.Home_Fragment;

public class MainActivity extends BaseActivity {
    private RadioButton mine_btn;
    private PDFView pdfView;
    private static boolean isExit = false;
    private final int BACK = 0;

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
        mine_btn=findViewById(R.id.Mine_Btn);


    }

    @Override
    protected void initListener() {
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
