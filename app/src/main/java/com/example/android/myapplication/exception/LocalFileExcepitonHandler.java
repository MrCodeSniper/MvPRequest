package com.example.android.myapplication.exception;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.android.myapplication.activity.MainActivity;
import com.example.android.myapplication.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Android on 2016/8/31.
 */
public class LocalFileExcepitonHandler extends BaseExceptionHandler {

    private Context context;

    public LocalFileExcepitonHandler(Context context) {
        super(context);
        this.context=context;
    }


    @Override
    public boolean handleUncaughtException(final Throwable ex) {
        if(ex==null){
            return  false;
        }



        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();//toast里有handler需要配合looper在子线程更新UI
                Toast.makeText(context,"出错了",Toast.LENGTH_SHORT).show();
                Log.e("tazzz",Thread.currentThread().getId()+"线程1");
                Looper.loop();

            }
        }.start();



        Log.e("tazzz",Thread.currentThread().getId()+"线程3");



        saveLog(ex);


        return true;
    }



    private void saveLog(Throwable e) {
        // TODO Auto-generated method stub

        OutputStream out;
        try {
            File errordir = new File(FileUtils.getDiskCacheDir(context)
                    + "/log");
            File errorfile = new File(FileUtils.getDiskCacheDir(context)
                    + "/log/crash.log");
            if (!errorfile.exists()) {
                errordir.mkdirs();
                errorfile.createNewFile();
            }
            out = new FileOutputStream(errorfile);
            out.write("\n--------------日志---------------\n".getBytes());
            PrintStream ps = new PrintStream(out);
            e.printStackTrace(ps);// ����ӡ����ݴ��������ӡ����
            ps.flush();
            out.flush();
            ps.close();
            out.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }
}
