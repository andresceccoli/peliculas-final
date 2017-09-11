package com.androidutn.peliculas.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static final String API_KEY = "d2da804e735404f8f3601949d1779038";
    private static final String API_URL = "https://api.themoviedb.org/3/";

    private static Retrofit retrofit;

    static {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        HttpUrl newUrl = originalRequest.url().newBuilder().addQueryParameter("api_key", API_KEY).build();
                        Request newRequest = originalRequest.newBuilder().url(newUrl).build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static MovieService getMovieService() {
        return retrofit.create(MovieService.class);
    }

}
