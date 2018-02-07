package proj.zs.com.hshs.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;
import proj.zs.com.hshs.fragment.Appreciate_Fragment;
import proj.zs.com.hshs.fragment.Fication_Fragment;
import proj.zs.com.hshs.fragment.Home_Fragment;
import proj.zs.com.hshs.fragment.Mine_Fragment;
import proj.zs.com.hshs.fragment.Ranking_Fragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private FrameLayout frameLayout;
    private RadioButton mine_btn,ficetion_btn,appreciate_btn,home_btn,ranking_bth;
    private PDFView pdfView;
    private ImageView menu_mbtn;
    private static boolean isExit = false;
    private final int BACK = 0;
    private ArrayList<Fragment> fragments;
    private RadioGroup rgGroup;

    private Appreciate_Fragment appreciate_fragment;
    private Fication_Fragment fication_fragment;
    private Home_Fragment home_fragment;
    private Mine_Fragment mine_fragment;
    private Ranking_Fragment ranking_fragment;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initData() {
       // frameLayout=findViewById(R.id.fl_container);
        mine_btn=findViewById(R.id.Mine_Btn);
        rgGroup=findViewById(R.id.Home_RGroup);
        //创建fragment实例并把他们加入集合
        fragments=new ArrayList<>();
        addFragment();
        //设置默认被选中的RadioButton
        rgGroup.check(R.id.Home_Btn_News);
        switchFragment(0);
        //radiogroup中的radiobutton的点击事件
        rgGroup.setOnCheckedChangeListener(this);

    }
    /**
     * 创建fragment实例加入集合
     */
    private void addFragment() {
        fragments.add(new Home_Fragment());
        fragments.add(new Fication_Fragment());
        fragments.add(new Ranking_Fragment());
        fragments.add(new Appreciate_Fragment());
        fragments.add(new Mine_Fragment());
    }

    /**
     * 点击切换fragment
     * @param position
     */
    private void switchFragment(int position) {
        //开启事物
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        //遍历集合
        for(int i=0;i<fragments.size();i++){
            Fragment fragment=fragments.get(i);
            if (i==position){
                //显示fragment
                if(fragment.isAdded()){
                    //如果这个fragment已经被事物添加，显示
                    fragmentTransaction.show(fragment);
                }else {
                    //如果这是fragment没有被事物添加，添加
                    fragmentTransaction.add(R.id.container,fragment);
                }
            }else {
                //隐藏fragment
                if(fragment.isAdded()){
                    //如果这个fragment已经被事物添加，隐藏
                    fragmentTransaction.hide(fragment);
                }
            }
        }
        //提交事物
        fragmentTransaction.commit();

    }

    @Override
    protected void initListener() {

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        /**
//         * 这里表示加载菜单文件，第一个草书表示通过那个资源文件来创建菜单
//         * 第二个表示将菜单传入那个对象中，这里用Menu传入menu
//         */
//        getMenuInflater().inflate(R.menu.main,menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.first:
//                Toast.makeText(this, "点击了消息列表", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.two:
//                Toast.makeText(this, "点击了产品追溯", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.three:
//                Toast.makeText(this, "点击了入库", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.four:
//                Toast.makeText(this, "点击了出库", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.five:
//                Toast.makeText(this, "点击了盘库", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//
//        }
//        return true;
//    }

    @Override
    protected void loadData() {

    }
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            /**
             * home_btn=findViewById(R.id.Home_Btn_News);
             ficetion_btn=findViewById(R.id.Fiction_Btn);
             ranking_bth=findViewById(R.id.Rankind_Btn);
             appreciate_btn=findViewById(R.id.Appreciate_Btn);
             mine_btn=findViewById(R.id.Mine_Btn);
             */
            case R.id.Home_Btn_News:
                switchFragment(0);
                break;
            case R.id.Fiction_Btn:
                switchFragment(1);
                break;
            case R.id.Rankind_Btn:
                switchFragment(2);
                break;
            case R.id.Appreciate_Btn:
                switchFragment(3);
                break;
            case R.id.Mine_Btn:
                switchFragment(4);
                break;
        }

    }
}
