package com.example.android.myapplication.exception;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.util.Log;

import com.example.android.myapplication.activity.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 处理未捕获异常的基类
 */
public abstract  class BaseExceptionHandler implements Thread.UncaughtExceptionHandler{


    private Context context;

    public BaseExceptionHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

         //处理完毕之后正常退出或者再次打开APP
        if(handleUncaughtException(ex)){

            try {
                //Toast显示时间
                Thread.sleep(1000);

            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            Intent intent=new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    100, intent, Intent.FLAG_ACTIVITY_NEW_TASK);

            AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            // RTC:锁屏了不执行任务
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 2000,
                    pendingIntent);
           //重启时间
            Log.e("tazzz",Thread.currentThread().getId()+"线程3");
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);// ��ֹJVM���� �����0���Ƿ����˳�

        }


    }


    //处理未捕获异常
    public abstract boolean handleUncaughtException(Throwable ex);
}

