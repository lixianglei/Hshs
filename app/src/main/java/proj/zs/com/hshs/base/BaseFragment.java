package proj.zs.com.hshs.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Unbinder bind;
    protected View view;
    protected Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        this.view = view;
        getView(view);
        initData();
        initListener();
        loadData();
    }

    public View getView(View view) {
        return this.view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 加载一个视图
     *
     * @return
     */
    protected abstract int layoutId();

    /**
     * 初始化对象
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            hid();
        } else {
            show();
        }
    }

    /**
     * 当fragment显示的时候执行
     */
    protected void show() {

    }

    /**
     * 当fragment隐藏的时候执行
     */
    protected void hid() {

    }


    /**
     * 设置fragment对应的标题
     */
    protected void setFragmentTitle() {

    }

    /**
     * fragment之间传递数据
     *
     * @param bundle
     */
    public void setParams(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
