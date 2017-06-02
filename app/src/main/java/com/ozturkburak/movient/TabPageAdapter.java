package com.ozturkburak.movient;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ozturkburak.Utils.Util;


/**
 * Created by burak on 4/30/17.
 */



/* We use a {android.support.v4.app.FragmentPagerAdapter}
 * derivative, which will keep every loaded fragment in memory.
 * If this becomes too memory intensive,
 * it may be best to switch to a {android.support.v4.app.FragmentStatePagerAdapter}.*/
public class TabPageAdapter extends FragmentPagerAdapter
{


    private static ViewPager ms_viewPager;
    private static TabPageAdapter ms_tabPageAdapter;
    private static TabLayout ms_tabLayout;

    public TabPageAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new Fragment1_List();
            case 1:
                return new Fragment2_favorite();
            case 2:
                return new Fragment3_Search();
            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return 3;       // Show 3 total pages
    }



    @Override
    public CharSequence getPageTitle(int position)
    {
        return  "";
    }


    public static void initTabLayout(Activity activity, FragmentManager fragmentManager)
    {
        ms_tabPageAdapter = new TabPageAdapter(fragmentManager);
        ms_viewPager = (ViewPager) activity.findViewById(R.id.MAINACTIVITY_VIEWPAGER_CONTAINER);
        ms_viewPager.setAdapter(ms_tabPageAdapter);


        ms_tabLayout = (TabLayout) activity.findViewById(R.id.MAINACTIVITY_TABLAYOUT_TABS);
        ms_tabLayout.setupWithViewPager(ms_viewPager);

        int tabIcons[] = {R.drawable.ic_date , R.drawable.ic_stars , R.drawable.ic_movie};

        for (int i = 0; i < ms_tabLayout.getTabCount(); i++) {
            ms_tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }

    }

    public static void setCurrentTab(Util.APP_PAGES pages)
    {
        ms_tabLayout.setScrollPosition(pages.getValue() , 0f , false);
        ms_viewPager.setCurrentItem(pages.getValue() , true);

    }

    public static Fragment getFragmentbyId(Util.APP_PAGES pages)
    {
        return ms_tabPageAdapter.getItem(pages.getValue());
    }


    public static void closeTab()
    {
        ms_tabPageAdapter.getItem(Util.APP_PAGES.LIST.getValue()).onPause();
    }

}

