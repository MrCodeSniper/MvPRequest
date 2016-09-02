package com.example.android.myapplication.presenter;

import android.content.Context;

import com.example.android.myapplication.bean.Root;
import com.example.android.myapplication.interfac.MenuView;
import com.example.android.myapplication.internet.ServerBinder;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by Android on 2016/9/1.
 */
public class ContributorPresenter extends MvpBasePresenter<MenuView> {

private Context context;
    public ContributorPresenter(Context context) {
        this.context=context;
    }

         public  void get(String url,String param_name,String param_index){
            final MenuView view = getView();
            view.onLoadStart();

            ServerBinder instance = ServerBinder.getInstance();
            instance.register(url,"ac33bd16a6e9cbbab9d7cb6acd9991fe","json","post",Root.class,context);
             instance.call("post", new ServerBinder.ServerCallback() {
                 @Override
                 public void onServerCallback(ServerBinder.ServerData data) {
                    view.onLoadComplete((Root) data.serverbean);
                 }
             },new String[]{param_name,param_index});


        }




    public void change(String name){
        MenuView view = getView();
        view.onChangeName(name);
    }













//    private Subscriber<Contributor> contributorSub = new Subscriber<Contributor>() {
//
//        @Override
//        public void onStart() {
//            MenuView view = getView();
//            if(view != null){
//                view.onLoadStart();
//            }
//        }
//
//        @Override
//        public void onCompleted() {
//
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onNext(Contributor topContributor) {
//            MenuView view = getView();
//            if(view != null){
//                view.onLoadComplete(topContributor);
//            }
//        }
//    };
//
//    public void get(String owner,String repo){
//        GitHubApi.getContributors(owner, repo)
//                .take(1)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .map(new Func1<List<Contributor>, Contributor>() {
//
//                    @Override
//                    public Contributor call(List<Contributor> contributors) {
//                        return contributors.get(0);
//                    }
//                })
//                .subscribe(contributorSub);
//    }
//
//
//    public void change(){
//        MenuView view = getView();
//        if(view != null){
//            view.onChangeName("zjutkz");
//        }
//    }












}
