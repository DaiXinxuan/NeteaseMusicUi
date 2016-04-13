package philips.com.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/3/30.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragments;

    public ViewPagerFragmentAdapter(FragmentManager fm, ArrayList<Fragment> arrayList) {
        super(fm);
        this.fragments = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position % fragments.size());
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
