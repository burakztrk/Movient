package com.ozturkburak.movient.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMovieData
{

    @SerializedName("movie_count") @Expose private Integer movieCount;
    @SerializedName("limit") @Expose private Integer limit;
    @SerializedName("page_number") @Expose private Integer pageNumber;
    @SerializedName("movies") @Expose private List<Movie> movies = null;


    public SearchMovieData setMovieCount(Integer movieCount) {
        this.movieCount = movieCount;
        return this;
    }

    public SearchMovieData setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public SearchMovieData setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public SearchMovieData setMovies(List<Movie> movies) {
        this.movies = movies;
        return this;
    }

    public Integer getMovieCount()
    {
        return movieCount;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public Integer getPageNumber()
    {
        return pageNumber;
    }

    public List<Movie> getMovies()
    {
        return movies;
    }
}