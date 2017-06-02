package com.ozturkburak.movient;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ozturkburak.Utils.Util;
import com.ozturkburak.movient.controller.IFragmentCommunicator;
import com.ozturkburak.movient.search.SearchOptions;
import com.wefika.horizontalpicker.HorizontalPicker;



public class Fragment3_Search extends Fragment implements View.OnClickListener
{
    private EditText m_editTextSearch;
    private HorizontalPicker m_pickerQuality, m_pickerGenre, m_pickerRaiting, m_pickerSortBy;
    private Button m_buttonSearch;

    public Fragment3_Search(){}

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState)
    { return inflater.inflate(R.layout.fragment3_search, container, false); }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.init();
    }



    private void init()
    {
        final Activity activity = getActivity();
        m_editTextSearch = (EditText) activity.findViewById(R.id.ACTIVITYSEARCH_EDITTEXT_SEARCHTEXT);

        m_pickerQuality = (HorizontalPicker) activity.findViewById(R.id.ACTIVITYSEARCH_PICKER_QUALITY);
        m_pickerGenre = (HorizontalPicker) activity.findViewById(R.id.ACTIVITYSEARCH_PICKER_GENRE);
        m_pickerRaiting = (HorizontalPicker) activity.findViewById(R.id.ACTIVITYSEARCH_PICKER_RAITING);
        m_pickerSortBy = (HorizontalPicker) activity.findViewById(R.id.ACTIVITYSEARCH_PICKER_SORTBY);

        m_buttonSearch = (Button) activity.findViewById(R.id.SEARCHFRAGMENT_BUTTON_SEARCH);
        m_buttonSearch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        String searchText = m_editTextSearch.getText().toString().trim();

        if (searchText.isEmpty())
        {
            m_editTextSearch.setError(getActivity().getString(R.string.notEmptyMessage));
            return;
        }

        SearchOptions options = new SearchOptions( SearchOptions.LIMIT_DEFAULT , SearchOptions.PAGE_DEFAULT , searchText , m_pickerQuality.getSelectedItem() , m_pickerGenre.getSelectedItem() , m_pickerRaiting.getSelectedItem() , m_pickerSortBy.getSelectedItem() , true) ;

        IFragmentCommunicator fragmentCommunicator = (IFragmentCommunicator)getActivity();
        fragmentCommunicator.sendData(Util.APP_PAGES.LIST , options);
        Util.hideKeyboard(getActivity());
    }

}

