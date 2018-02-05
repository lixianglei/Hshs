package proj.zs.com.hshs.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zengshi on 2018/2/2.
 * 修改密码Activity
 */

public class ModifyPWD_Activity extends BaseActivity {
    private TextView textView;
    private ImageView back;
    @Override
    protected int layoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initData() {
        textView=findViewById(R.id.details_text);
        back=findViewById(R.id.iv_back);

    }

    @Override
    protected void initListener() {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModifyPWD_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }

        });

    }

    @Override
    protected void loadData() {

    }
}
