package proj.zs.com.hshs.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zengshi on 2018/2/2.
 * 修改密码Activity
 */

public class ModifyPWD_Activity extends BaseActivity implements TextWatcher,View.OnClickListener {
    private EditText et_orgin;
    private EditText et_new;
    private EditText et_confirm;
    private Button bt_confirm;
    private ImageView back;
    @Override
    protected int layoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initData() {
        et_orgin= (EditText) findViewById(R.id.id_orgin_et);
        et_confirm= (EditText) findViewById(R.id.id_confirm_et);
        et_new= (EditText) findViewById(R.id.id_new_et);
        bt_confirm= (Button) findViewById(R.id.id_confirm_bt);
        back=findViewById(R.id.iv_back);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initListener() {
        et_confirm.addTextChangedListener((TextWatcher) this);
        et_orgin.addTextChangedListener((TextWatcher) this);
        et_new.addTextChangedListener((TextWatcher) this);
        bt_confirm.setSelected(false);
        bt_confirm.setOnClickListener((View.OnClickListener) this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        bt_confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
        et_orgin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clearAll();
                return false;
            }
        });
        et_new.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clearAll();
                return false;
            }
        });
        et_confirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clearAll();
                return false;
            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        /**
         * 当三个EditText的内容都不为空的时候，
         * Button为蓝色，否则为灰色通过
         * bt_confirm.setSelected(true)实现蓝色，
         *  bt_confirm.setSelected(false);实现灰色
         */
        if(!TextUtils.isEmpty(et_confirm.getText().toString())&&!TextUtils.isEmpty(et_orgin.getText().toString())
                &&!TextUtils.isEmpty(et_new.getText().toString())){
            bt_confirm.setSelected(true);
        }else{
            bt_confirm.setSelected(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private ProgressDialog dialog;
    public void onClick(View v) {
        if(checkNull()){
            return;
        }
        if(!et_confirm.getText().toString().equals(et_new.getText().toString())){
            et_confirm.setText("");
            requstFocus(et_confirm, "两次密码不一致", Color.RED,true);
            return;
        }
        dialog=ProgressDialog.show(this,"","修改中,请稍后...",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!"123456".equals(et_orgin.getText().toString())){
                    et_orgin.setText("");
                    requstFocus(et_orgin, "原密码错误", Color.RED, true);
                }else{
                    Toast.makeText(ModifyPWD_Activity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ModifyPWD_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                dialog.dismiss();
            }
        },3000);
    }

    private boolean checkNull() {
        if(TextUtils.isEmpty(et_orgin.getText().toString())){
            requstFocus(et_orgin, null, Color.GRAY,true);
            return true;
        }
        if(et_orgin.getText().toString().length()<6){
            et_orgin.setText("");
            requstFocus(et_orgin, "原密码格式错误", Color.RED,true);
            return true;
        }
        if(TextUtils.isEmpty(et_new.getText().toString())){
            requstFocus(et_new, null, Color.GRAY,true);
            return true;
        }
        if(et_new.getText().toString().length()<6){
            et_new.setText("");
            requstFocus(et_new, "新密码格式错误", Color.RED,true);
            return true;
        }
        if(TextUtils.isEmpty(et_confirm.getText().toString())){
            requstFocus(et_confirm,null, Color.GRAY,true);
            return true;
        }
        return false;
    }
    public void requstFocus(EditText et,String hint,int hintColor,boolean needFocus) {
        if (hint == null) {
            hint = "请输入密码";
        }
        et.setHint(hint);
        et.setHintTextColor(hintColor);
        if (needFocus) {
            et.requestFocus();
        }
    }
    public void clearAll(){
        requstFocus(et_orgin, null, Color.GRAY,false);
        requstFocus(et_new, null, Color.GRAY,false);
        requstFocus(et_confirm,null, Color.GRAY,false);
    }
}
