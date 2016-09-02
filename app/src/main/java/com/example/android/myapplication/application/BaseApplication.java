package com.example.android.myapplication.application;

import android.app.Application;
import android.content.Context;

import com.example.android.myapplication.exception.BaseExceptionHandler;
import com.example.android.myapplication.exception.LocalFileExcepitonHandler;

/**
 * Created by Android on 2016/8/31.
 */
public abstract class BaseApplication  extends Application {


    private Context applicationcontext;


    @Override
    public void onCreate() {
        super.onCreate();

        applicationcontext=getApplicationContext();


//        setExceptionHandler(applicationcontext);//设置未捕获异常


    }

    private void setExceptionHandler(Context context) {
        if(getDefaultExceptionHandler()==null){//没有未捕获异常处理事件
            Thread.setDefaultUncaughtExceptionHandler(new LocalFileExcepitonHandler(context));
        }else {
            Thread.setDefaultUncaughtExceptionHandler(getDefaultExceptionHandler());//自己设置的
        }
    }


    public abstract BaseExceptionHandler getDefaultExceptionHandler();

}
