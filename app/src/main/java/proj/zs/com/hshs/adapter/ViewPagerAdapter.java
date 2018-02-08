package proj.zs.com.hshs.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import proj.zs.com.hshs.fragment.PageFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    private String tabTitles[] = new String[]{ "我的书", "我的分享", "关注的用户"};
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}