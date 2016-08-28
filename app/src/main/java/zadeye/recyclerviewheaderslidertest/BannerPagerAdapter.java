package zadeye.recyclerviewheaderslidertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by User on 08-Mar-16.
 */
public class BannerPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<BannerFragment> bannerFragments;

    public BannerPagerAdapter(FragmentManager fm,
                              ArrayList<BannerFragment> bannerFragments) {
        super(fm);
        this.bannerFragments = bannerFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return bannerFragments.get(position);
    }

    @Override
    public int getCount() {
        return bannerFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
