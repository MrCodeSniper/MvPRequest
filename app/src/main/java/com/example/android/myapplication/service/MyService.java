package com.example.android.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Android on 2016/8/31.
 */
public class MyService extends Service {

    private  MyBinder binder;


    @Override
    public void onCreate() {
        super.onCreate();
      binder=new MyBinder();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }





    public class  MyBinder extends Binder {

         public void getdata(){
             try {
                 Thread.sleep(18000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

    }


}
