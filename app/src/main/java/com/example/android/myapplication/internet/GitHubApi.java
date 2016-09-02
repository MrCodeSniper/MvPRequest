package com.example.android.myapplication.internet;

import com.example.android.myapplication.bean.Contributor;
import com.example.android.myapplication.service.GitHubService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Android on 2016/9/1.
 */
public class GitHubApi {
    public static Observable<List<Contributor>> getContributors(String owner, String repo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        return service.repoContributors(owner,repo);
    }














}
