package android.small.indeedfortunate.xqsmallnormal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 旧城丷 on 2018/12/5.
 */

class CacheFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;

    public CacheFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
   return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
