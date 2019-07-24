package com.example.dell.projectdemo.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.projectdemo.LehngaFragment;
import com.example.dell.projectdemo.NewArrivalFragment;
import com.example.dell.projectdemo.R;
import com.example.dell.projectdemo.SareeFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.NewArrival, R.string.Saree,R.string.Lehnga};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
       switch (position) {
           case 0:
               NewArrivalFragment tab1 =new NewArrivalFragment();
               return tab1;
           case 1:
               SareeFragment tab2 =new SareeFragment();
               return tab2;
           case 2:
               LehngaFragment tab3 =new LehngaFragment();
               return tab3;
               default:
                   return null;
       }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);

    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}