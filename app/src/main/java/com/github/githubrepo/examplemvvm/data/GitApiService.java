/**
 * Created By Ravichandra S V.

 */

package com.github.githubrepo.examplemvvm.data;

import java.util.List;
import java.util.Map;

import com.github.githubrepo.examplemvvm.model.repoModel.Repository;
import com.github.githubrepo.examplemvvm.model.repoModel.RequestAdd;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import io.reactivex.Observable;
import retrofit2.http.POST;

public interface GitApiService {
    @GET("user/repos")
    Observable<List<Repository>> getrepository(@HeaderMap Map<String, String> headers);
    @POST("user/repos")
    Observable<List<Repository>> addRepo(@HeaderMap Map<String, String> headers, @Body RequestAdd requestAdd);

}
