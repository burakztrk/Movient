package com.ozturkburak.movient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMovieModel
{

    @SerializedName("status") @Expose private String status;
    @SerializedName("data") @Expose private SearchMovieData SearchMovieData;


    public SearchMovieModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public SearchMovieModel setSearchMovieData(SearchMovieData SearchMovieData) {
        this.SearchMovieData = SearchMovieData;
        return this;
    }

    public String getStatus()
    {
        return status;
    }

    public SearchMovieData getSearchMovieData()
    {
        return SearchMovieData;
    }
}