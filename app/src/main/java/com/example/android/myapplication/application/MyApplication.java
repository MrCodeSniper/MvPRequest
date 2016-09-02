package com.example.android.myapplication.application;

import com.example.android.myapplication.exception.BaseExceptionHandler;

/**
 * Created by Android on 2016/8/31.
 */
public class MyApplication extends BaseApplication {

    @Override
    public BaseExceptionHandler getDefaultExceptionHandler() {
        return null;
    }
}
