package com.example.android.myapplication.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.myapplication.presenter.ContributorPresenter;
import com.example.android.myapplication.R;
import com.example.android.myapplication.bean.Contributor;
import com.example.android.myapplication.bean.Root;
import com.example.android.myapplication.interfac.MenuView;
import com.example.android.myapplication.internet.ServerBinder;
import com.example.android.myapplication.service.MyService;
import com.example.android.myapplication.utils.IOUtils;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends MvpActivity<MenuView,ContributorPresenter> implements MenuView {

    private Button button;
    public TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv2= (TextView) findViewById(R.id.tv);
        button= (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               get();
            }
        });

    }

    @NonNull
    @Override
    public ContributorPresenter createPresenter() {
        return new ContributorPresenter(MainActivity.this);
    }

    @Override
    public void onLoadStart() {
          tv2.setText("开始加载");
    }

    @Override
    public void onLoadComplete(Root root) {
        tv2.setText("加载完成"+root.getData().toString());
    }


    public void get(){
        getPresenter().get("http://apis.juhe.cn/cook/queryid","id","3");
    }

    @Override
    public void onChangeName(String name) {
      tv2.setText(name);
    }

    private void getDataFromNet(String post) {
        ServerBinder instance = ServerBinder.getInstance();
        instance.register(post,"ac33bd16a6e9cbbab9d7cb6acd9991fe","json","post",Root.class,MainActivity.this);
        instance.call("post", new ServerBinder.ServerCallback() {
            @Override
            public void onServerCallback(ServerBinder.ServerData data) {
                Root root= (Root) data.serverbean;
                tv2.setText(root.getData().toString());

            }
        },new String[]{"id","3"});
    }
}
