package com.example.sudhar.userinterface;

/**
 * Created by Sudhar on 14-01-2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager1 extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Pager1(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
               return new Database1();

            case 1:

               return new Database2();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}