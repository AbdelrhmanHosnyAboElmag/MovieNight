package com.hosny.moive_night.Utils;

import com.hosny.moive_night.Models.MoiveModel;
import com.hosny.moive_night.Response.MoiveSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoiveApi {
    //search for moives
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher&page=1<page done for perfect>
    @GET("/3/search/movie")
    Call<MoiveSearchResponse> searchMoive(
            @Query("api_key") String key,@Query("query") String query,
            @Query("page") String page
    );
    //https://api.themoviedb.org/3/movie/550?api_key=2dcf2f3659662d6551bacf185da9a825
    @GET("3/movie/{moive_id}")
    Call<MoiveModel>getmoive(
            @Path("moive_id") int id,
            @Query("api_key") String api_key
    );
    //https://api.themoviedb.org/3/movie/popular?api_key=2dcf2f3659662d6551bacf185da9a825&page=2
    @GET("3/movie/popular")
    Call<MoiveSearchResponse> getpopular(
            @Query("api_key") String key,
            @Query("page") String page
    );
}
