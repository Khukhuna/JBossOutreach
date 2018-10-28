package com.khukhuna.jbossoutreach.network;

import com.khukhuna.jbossoutreach.models.Repository;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("orgs/{organization}/repos/")
    Call<List<Repository>> getProfile(@Path("organization") String organization);

}