package com.example.android.myapplication.interfac;

import com.example.android.myapplication.bean.Contributor;
import com.example.android.myapplication.bean.Root;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Android on 2016/9/1.
 */
public interface MenuView extends MvpView {
    //开始加载
    void onLoadStart();
//加载成功
    void onLoadComplete(Root root);
//改名
    void onChangeName(String name);


}
