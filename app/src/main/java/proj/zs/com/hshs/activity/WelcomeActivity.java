package proj.zs.com.hshs.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.internal.Utils;
import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zengshi on 2018/1/31.
 */

public class WelcomeActivity extends Activity {
    private String url="";
    private ImageView imageView;
    Bitmap bitmap=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_acticvity);
        imageView=findViewById(R.id.welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //开启一个子线程，进行网络操作，等待返回结果，用handler通知UI
//        new Thread(networkTask).start();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
//                startActivity(intent);
//                WelcomeActivity.this.finish();
//
//            }
//        },2000);
//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Bundle data =msg.getData();
//                Bitmap bitmap=data.getParcelable("bitmap");
//                //UI界面的更新操作
//                imageView.setImageBitmap(bitmap);
//            }
//        };
//        /**
//         * 网络相关的子线程
//         */
//        Runnable networkTask=new Runnable() {
//            @Override
//            public void run() {
//                //在这里进行网络请求操作
//                Message message=new Message();
//                Bundle data=new Bundle();
//                Bitmap bitmap= proj.zs.com.hshs.utils.Utils.getBitmap(url);
//                data.putParcelable("bitmap",bitmap);
//                message.setData(data);
//                handler.sendMessage(message);
//            }
//        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
