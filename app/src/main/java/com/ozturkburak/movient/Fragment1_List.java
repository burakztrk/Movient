package com.ozturkburak.movient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ozturkburak.Utils.Util;
import com.ozturkburak.movient.controller.RetrofitClient;
import com.ozturkburak.movient.model.*;
import com.ozturkburak.movient.resultlist.*;
import com.ozturkburak.movient.search.SearchOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ozturkburak.movient.search.SearchOptions.ms_searchOptions;

/*
 * TODO: api optionslari enum gibi yap
 * TODO: loadNext metodunu parametreli hale getirip diger fragmentden ulasilabilir yap
 * TODO: Check Connection her xml cekiste
*/
public class Fragment1_List extends Fragment
{
    private RecyclerView recyclerView;
    private PosterAdapter adapter;
    private List<Movie> movieList;



    public Fragment1_List()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment1_list, container, false);

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.FRAGMENT_RECYCLERVIEW);
        movieList = new ArrayList<>();
        adapter = new PosterAdapter(getActivity(), movieList);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager)
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view)
            {
                loadNextDataFromApi(++page);
            }
        });

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(getContext(), 3,3, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        if (ms_searchOptions == null)
        {
            ms_searchOptions  = new SearchOptions();
        }

        loadNextDataFromApi(1);

        super.onViewCreated(view, savedInstanceState);
    }





    public void loadNextDataFromApi(int offset)
    {
        ms_searchOptions.setPage(offset);
        Call<SearchMovieModel> call = RetrofitClient.getSearchMovieCallBack(ms_searchOptions);
        Log.d("DEBUG-LINK", call.request().toString());

        call.enqueue(new Callback<SearchMovieModel>()
        {

            @Override
            public void onResponse(Call<SearchMovieModel> call, Response<SearchMovieModel> response)
            {
                if (response.body() == null)
                    return;

                SearchMovieData SearchMovieData = response.body().getSearchMovieData();
                int count = SearchMovieData.getMovieCount();


                if (count == 0) //Sonuc bos donerse
                {
                    Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                    TabPageAdapter.setCurrentTab(Util.APP_PAGES.SEARCH);
                    return;
                }

                if (SearchMovieData.getMovies() == null ) //liste sonu
                    return;

                List<Movie> movies = SearchMovieData.getMovies();
                for (int i = 0; i < movies.size(); i++)
                {
                    movieList.add(movies.get(i));
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<SearchMovieModel> call, Throwable t)
            {
                Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }


    public void setQueryData(SearchOptions searchOptions )
    {

        //TODO : static yapidan kurtul
        ms_searchOptions =  searchOptions;
        Log.d("setQueryData" , String.valueOf(ms_searchOptions.getSearchText()));
    }
}
