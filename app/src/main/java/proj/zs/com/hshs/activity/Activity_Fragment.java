package proj.zs.com.hshs.activity;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zsz on 2018/2/5.
 */

public class Activity_Fragment extends BaseActivity {
    private Fragment[] mFragments;
    private RadioGroup bottomRg;
    private android.app.FragmentManager fragmentManager;
    private android.app.FragmentTransaction fragmentTransaction;
    private RadioButton app_btn,fic_btn,home_btn,mine_bnt,ranking_btn;
    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mFragments=new Fragment[5];
        fragmentManager=this.getFragmentManager();
        mFragments[0]=fragmentManager.findFragmentById(R.id.Home_Btn_News);
        mFragments[1]=fragmentManager.findFragmentById(R.id.Fiction_Btn);
        mFragments[2]=fragmentManager.findFragmentById(R.id.Rankind_Btn);
        mFragments[3]=fragmentManager.findFragmentById(R.id.Appreciate_Btn);
        mFragments[4]=fragmentManager.findFragmentById(R.id.Mine_Btn);
        fragmentTransaction=fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]);
        fragmentTransaction.show(mFragments[0]).commit();
        setFragmentIndictor();
    }

    private void setFragmentIndictor() {
        this.bottomRg=findViewById(R.id.Home_RGroup);
        this.home_btn=findViewById(R.id.Home_Btn_News);
        this.fic_btn=findViewById(R.id.Fiction_Btn);
        this.ranking_btn=findViewById(R.id.Rankind_Btn);
        this.app_btn=findViewById(R.id.Appreciate_Btn);
        this.mine_bnt=findViewById(R.id.Mine_Btn);
    }

    @Override
    protected void initListener() {
        bottomRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction=fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]);
                switch (checkedId){
                    case R.id.Home_Btn_News:
                        fragmentTransaction.show(mFragments[0]).commit();
                        break;
                    case R.id.Fiction_Btn:
                        fragmentTransaction.show(mFragments[1]).commit();
                        break;
                    case R.id.Rankind_Btn:
                        fragmentTransaction.show(mFragments[2]).commit();
                        break;
                    case R.id.Appreciate_Btn:
                        fragmentTransaction.show(mFragments[3]).commit();
                        break;
                    case R.id.Mine_Btn:
                        fragmentTransaction.show(mFragments[4]).commit();
                        break;
                        default:
                            break;
                }
            }
        });

    }

    @Override
    protected void loadData() {

    }
}
