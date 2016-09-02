# MvPRequest
基于MVP架构针对服务器使用volley和gson封装的网络模块

使用事项：
1.创建ServerBinder建立连接

ServerBinder instance = ServerBinder.getInstance();

2.注册 （url，key，返回的格式，请求方式，实体类，context）

instance.register(post,"ac33bd16a6e9cbbab9d7cb6acd9991fe","json","post",Root.class,MainActivity.this);

3.调用call方法再回调中得到实体类对象并进行UI更新 参数（请求方式，callback，参数）
 instance.call("post", new ServerBinder.ServerCallback() {
            @Override
            public void onServerCallback(ServerBinder.ServerData data) {
                Root root= (Root) data.serverbean;
                tv2.setText(root.getData().toString());
            }
      },new String[]{"id","3"});
      
缺点：针对服务器链接封装的，可能不适用于其他人，但重要的是封装的思想
