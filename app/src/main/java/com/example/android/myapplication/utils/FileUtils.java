package com.example.android.myapplication.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

/**
 * Created by Android on 2016/8/31.
 */
public class FileUtils {


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static String getDiskCacheDir(Context context){
        String cachePath;
        //�����sd��
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)||!Environment.isExternalStorageRemovable()){//�ⲿ�ڴ治�����Ƴ�״̬
            cachePath=context.getExternalCacheDir().getAbsolutePath();//�õ�sd������·��
        }else {
            cachePath=context.getCacheDir().getAbsolutePath();
        }
        return cachePath;
    }

}
