package proj.zs.com.hshs.activity;

import android.view.KeyEvent;

import proj.zs.com.hshs.R;
import proj.zs.com.hshs.base.BaseActivity;

/**
 * Created by zsz on 2018/2/2.
 */

public class PersonActivity extends BaseActivity {
    @Override
    protected int layoutId() {
        return R.layout.me_fragment_layout;
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
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
