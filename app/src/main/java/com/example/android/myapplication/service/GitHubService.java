package com.example.android.myapplication.service;

import com.example.android.myapplication.bean.Contributor;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;



/**
 * Created by kangzhe on 16/4/7.
 */
public interface GitHubService {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}