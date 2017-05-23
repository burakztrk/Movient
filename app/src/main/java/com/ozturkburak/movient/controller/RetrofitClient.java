package com.ozturkburak.movient.controller;

import com.ozturkburak.movient.model.MovieDetailModel;
import com.ozturkburak.movient.model.SearchMovieModel;
import com.ozturkburak.movient.search.SearchOptions;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by burak on 5/5/17.
 */

public class RetrofitClient
{

    public static final String BASE_URL ="https://yts.ag/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient ()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static Call<SearchMovieModel> getSearchMovieCallBack(SearchOptions options)
    {
        IRestService restInterfaceController = getClient().create(IRestService.class);
        Call<SearchMovieModel> call = restInterfaceController.searchMovie(
                options.getLimit() ,
                options.getPage() ,
                options.getQualityVal() ,
                options.getRatingVal() ,
                options.getSearchText() ,
                options.getGenreVal(),
                options.getSortByVal() ,
                options.isWithRtRating());
        return call;
    }


    public static Call<MovieDetailModel> getMovieDetailCallBack(String movieId)
    {
        IRestService restInterfaceController = getClient().create(IRestService.class);
        Call<MovieDetailModel> call = restInterfaceController.detailMovie(movieId , true);

        return call;
    }
}