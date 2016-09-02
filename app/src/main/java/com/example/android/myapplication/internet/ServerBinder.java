package com.example.android.myapplication.internet;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.myapplication.bean.BaseBean;
import com.example.android.myapplication.interfac.ServerImp;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于volley和gson的网络解析封装框架
 * Created by Android on 2016/9/1.
 */
public class ServerBinder implements ServerImp{

    //保存所有注册的数据，当然要保存了，不保存怎么调用？
    private HashMap<String, BindData> mBindDatas;

    private Context context;
    //单例
    private static ServerBinder sBinder = null;


    private ServerBinder() {

    }

   // 只允许一个线程访问
    public synchronized  static  ServerBinder getInstance(){
       if(sBinder==null){
           sBinder=new ServerBinder();
       }
        return  sBinder;
    }



    private  ServerCallback serverCallback;

    @Override
    public void get(final BindData bindData, String[] params, final ServerCallback cb) {
        String url=bindData.addr;
        String key=bindData.key;
        String dtype=bindData.dtype;
        String id_name=params[0];
        String id=params[1];
        String param=url+"?"+"key="+key+"&"+"dtype="+dtype+"&"+"id="+id;
        Log.e("tazzz",param);
        // Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, param,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        handleResponce(json,cb,bindData);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }

    @Override
    public void post(final BindData bindData, final String[] params, final ServerCallback cb) {
        String url = bindData.addr;
        final String key=bindData.key;
        final String dtype=bindData.dtype;
        String id_name=params[0];
        String id=params[1];

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        handleResponce(json,cb,bindData);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", key);
                map.put("dtype", dtype);
                map.put(params[0],params[1]);
                return map;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);

    }

    //客户端回调接口
    public interface ServerCallback{
        //status 表示网络请求状态，bindData表示当前请求相关参数，record表示返回数据
        void onServerCallback(ServerData data);
    }

   public void setServerCallback(ServerCallback callback){
       this.serverCallback=callback;
   }



    private void handleResponce(String jsonstr,ServerCallback callback,BindData bindData){


        try {
            JSONObject jsonObj = new JSONObject(jsonstr);
            ServerData serverData = new ServerData();
            serverData.bindData=bindData;
            serverData.resultcode=jsonObj.getInt("resultcode");
            serverData.reason=jsonObj.getString("reason");
            if(serverData.resultcode==200){
                String data=jsonObj.getJSONObject("result").toString();
                serverData.serverbean= (BaseBean) new Gson().fromJson(data,bindData.recordClass);
            }

            callback.onServerCallback(serverData);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


   public void register(String addr,String key,String dtype,String type,Class<?> bean,Context context){
      this.context=context;
       mBindDatas=new HashMap<>();
       BindData bindData=new BindData();

       bindData.addr=addr;
       bindData.dtype=dtype;
       bindData.key=key;
       bindData.recordClass=bean;
       bindData.type=type;
       //把数据存起来
       mBindDatas.put(type, bindData);
   }



    public void call(String type, ServerCallback cb, String[] params){
        if(!mBindDatas.containsKey(type)){
            return;
        }
        BindData bindData = mBindDatas.get(type);
        switch(bindData.type){
            case "get":
                get(bindData, params, cb);
                break;
            case "post":
                post(bindData,params, cb);
                break;
//            case "download":
//                download(bindData, params, cb);
//                break;
//            case "upload":
//                upload(bindData,params, cb);
//                break;
        }
    }





    //服务端返回数据
    public static class ServerData{
        public BindData bindData;//注册数据，让你分辨是什么接口及参数
        public BaseBean serverbean;//服务端返回的数据
        public int resultcode;//结果码
        public String reason;//服务端返回的错误或提示信息
    }

    //表示服务端数据
    public static class BindData{
        public String addr;//服务端地址
        public String key;//服务端代码入口
        public String dtype;
        public String type;
        public Class <?> recordClass;//返回record类型
    }





}
