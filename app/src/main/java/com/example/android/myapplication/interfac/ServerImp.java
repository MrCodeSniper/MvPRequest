package com.example.android.myapplication.interfac;

import com.example.android.myapplication.internet.ServerBinder;

/**
 * Created by Android on 2016/9/1.
 */
public interface ServerImp {


    public void get(ServerBinder.BindData bindData, String[]params, ServerBinder.ServerCallback cb);


    public void post(ServerBinder.BindData bindData, String[]params, ServerBinder.ServerCallback cb);



}
