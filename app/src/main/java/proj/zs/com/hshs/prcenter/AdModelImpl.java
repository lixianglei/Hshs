package proj.zs.com.hshs.prcenter;


import okhttp3.ResponseBody;
import proj.zs.com.hshs.activity.AdContract;
import proj.zs.com.hshs.model.bean.AdMessageBean;
import proj.zs.com.hshs.model.bean.LoginCheckBean;
import proj.zs.com.hshs.utils.RetrofitService;
import proj.zs.com.hshs.utils.RetrofitServiceInstance;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zone on 2017/03/26
 */

public class AdModelImpl implements AdContract.Model {
    RetrofitService retrofitService;
    public AdModelImpl() {
        retrofitService = RetrofitServiceInstance.getInstance();
    }
    public Observable<LoginCheckBean> getLoginCheck() {//假装服务器要展示广告
        return Observable.create(new Observable.OnSubscribe<LoginCheckBean>() {
            @Override
            public void call(Subscriber<? super LoginCheckBean> subscriber) {
                subscriber.onNext(new LoginCheckBean(true));
                subscriber.onCompleted();
            }
        });
    }

    public Observable<AdMessageBean> getAdMessage() {
        return Observable.create(new Observable.OnSubscribe<AdMessageBean>() {
            @Override
            public void call(Subscriber<? super AdMessageBean> subscriber) {//假装要展示 3 秒广告，且广告图为如下图
                subscriber.onNext(new AdMessageBean(3,"http://odjfpxwey.bkt.clouddn.com/2017-3-3-20-141110180-Screenshot_2017-02-23-23-10-26-062_com.tmall.wireless.png","http://www.baidu.com"));
                subscriber.onCompleted();
            }
        });
    }

    public Observable<ResponseBody> downLoadFile(String fileUrl) {
        return retrofitService.downLoadFile(fileUrl);
    }
}