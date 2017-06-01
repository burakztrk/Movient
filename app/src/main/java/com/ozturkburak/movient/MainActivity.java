package com.ozturkburak.movient;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ozturkburak.movient.controller.IFragmentCommunicator;
import com.ozturkburak.movient.search.SearchOptions;

import com.ozturkburak.Utils.Util.*;


public class MainActivity extends AppCompatActivity implements IFragmentCommunicator
{
    private CollapsingToolbarLayout collapsingToolbarLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collapsingToolbarInit();
        TabPageAdapter.initTabLayout(this,  getSupportFragmentManager());

    }


    private void collapsingToolbarInit()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.ACTIVITY_TOOLBAR);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ACTIVITY_COLLAPSINGTOOLBAR);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));

        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);

    }


    @Override
    public void sendData(APP_PAGES whichPage, Object obj)
    {
        switch (whichPage)
        {
            case LIST:
                Fragment1_List fragment1 = (Fragment1_List) TabPageAdapter.getFragmentbyId(whichPage);
                fragment1.setQueryData((SearchOptions) obj );
                TabPageAdapter.setCurrentTab(whichPage);
                break;

            default:
                break;
        }
    }
}
