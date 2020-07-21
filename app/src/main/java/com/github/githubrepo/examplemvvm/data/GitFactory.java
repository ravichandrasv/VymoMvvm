/**
 * Created By Ravichandra S V.
 */
package com.github.githubrepo.examplemvvm.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class GitFactory {
    /*
     * Base Url For Git Hub Repo Access
     * */
    public final static String BASE_URL = "https://api.github.com/";


    public static GitApiService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GitApiService.class);
    }
}
