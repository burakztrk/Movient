package com.ozturkburak.movient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2_favorite extends Fragment
{
    public Fragment2_favorite() {}



    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment2_favorite, container, false);
    }

}