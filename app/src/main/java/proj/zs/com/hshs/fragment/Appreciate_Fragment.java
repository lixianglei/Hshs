package proj.zs.com.hshs.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseApplication;
import proj.zs.com.hshs.base.BaseFragment;


/**
 * Created by zengshi on 2018/1/29.
 * 欣赏Fragment
 */

public class Appreciate_Fragment extends BaseFragment {
    private ImageView popu_image;
    private RelativeLayout cli;

    @Override
    protected int layoutId() {
        return R.layout.appreciate_fragment;
    }

    @Override
    protected void initData() {
        popu_image=view.findViewById(R.id.app_popu);
        cli=view.findViewById(R.id.click);

    }

    @Override
    protected void initListener() {
        cli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow(v);
            }
        });

    }

    private void initPopWindow(View v) {
        View view=LayoutInflater.from(BaseApplication.getInstance().context).inflate(R.layout.item_popup,null,false);
        LinearLayout xiaoxi=view.findViewById(R.id.popu_message);
        LinearLayout chanpin=v.findViewById(R.id.popu_chanpinzhuisu);
        LinearLayout ruku=v.findViewById(R.id.popu_ruku);
        LinearLayout chuku=v.findViewById(R.id.popu_chuku);
        LinearLayout panku=view.findViewById(R.id.popu_panku);
        //构造一个PopuWindow,参数依次是加载view，宽、高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画
        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 50, 0);
        //设置popupWindow里的按钮的事件
    }

    @Override
    protected void loadData() {

    }

}