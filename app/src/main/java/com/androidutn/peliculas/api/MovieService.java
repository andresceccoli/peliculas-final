package com.androidutn.peliculas.api;

import com.androidutn.peliculas.model.Pelicula;
import com.androidutn.peliculas.model.TmdbQueryResult;
import com.androidutn.peliculas.model.TmdbVideosResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("search/movie")
    Call<TmdbQueryResult> buscarPeliculas(@Query("query") String query);
    @GET("movie/{movie_id}")
    Call<Pelicula> buscarPelicula(@Path("movie_id") int id);
    @GET("movie/{movie_id}/videos")
    Call<TmdbVideosResult> buscarVideos(@Path("movie_id") int id);

}
