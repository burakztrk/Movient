package com.ozturkburak.movient.controller;

import com.ozturkburak.movient.model.MovieDetailModel;
import com.ozturkburak.movient.model.SearchMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by burak on 5/5/17.
 */

public interface IRestService
{

    /*
https://yts.ag/api/v2/list_movies.json?
limit=10&
page=1&
quality=1080p&
minimum_rating=7&
query_term=star&
genre=action&
sort_by=rating&
with_rt_ratings=true

https://yts.ag/api/v2/list_movies.json?limit=10&page=1&quality=1080p&minimum_rating=7&query_term=star&genre=action&sort_by=rating&with_rt_ratings=true

 */
    @GET("api/v2/list_movies.json?")
    Call<SearchMovieModel> searchMovie(@Query("limit") String limit ,
                                       @Query("page") String page ,
                                       @Query("quality") String quality ,
                                       @Query("minimum_rating") String minRaiting ,
                                       @Query("query_term") String query ,
                                       @Query("genre") String genre ,
                                       @Query("sort_by") String sortBy ,
                                       @Query("with_rt_ratings") boolean rtRatings );


    /*
    * https://yts.ag/api/v2/movie_details.json?movie_id=1&with_cast=true
    * https://yts.ag/api/v2/movie_details.json?
    * movie_id=15&
    * with_cast=true
    */

    @GET("api/v2/movie_details.json?")
    Call<MovieDetailModel> detailMovie(@Query("movie_id") String movieId , @Query("with_cast") boolean withCast );

}
