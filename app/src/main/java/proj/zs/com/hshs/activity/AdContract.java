package proj.zs.com.hshs.activity;

import android.graphics.Bitmap;

import proj.zs.com.hshs.base.IBaseView;
import proj.zs.com.hshs.model.bean.LoginCheckBean;


/**
 * Created by zengshi on 2017/3/26.
 */

public class AdContract {
public interface View extends IBaseView {
    void setAdTime(int count);

    void setLoginCheckBean(LoginCheckBean loginCheckBean);

    void setAdImg(Bitmap bitmap);
}

public interface Presenter {
}

public interface Model{
}


}