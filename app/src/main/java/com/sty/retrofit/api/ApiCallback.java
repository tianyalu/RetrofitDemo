package com.sty.retrofit.api;


import android.support.annotation.MainThread;
import android.util.Log;

import com.sty.retrofit.event.ApiCancelEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tian on 2018/7/3.
 */

public abstract class ApiCallback<T> implements Callback<T> {
    private Class<?> mClz;
    private boolean isCancel;

    public ApiCallback(Class<?> clz){
        mClz = clz;
        EventBus.getDefault().register(this);
    }

    @Subscribe()
    public void onEvent(ApiCancelEvent event){
        if(mClz != null && event.getClz() != null && mClz.getName().equals(event.getClz().getName())){
            isCancel = true;
        }
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try{
            if(isCancel){
                return;
            }
            if(EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().unregister(this);
            }

            T t = response.body();
            if(t != null){
                Log.i("sty", "result:" + t.toString());
            }

            if(response.isSuccessful()){
                success(call, response, response.body());
            }else {
                error(call, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        try{
            if(isCancel){
                return;
            }
            if(EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().unregister(this);
            }
            failure(call, t);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void success(Call<T> call, Response<T> response, T result);

    public abstract void error(Call<T> call, Response<T> response);

    public abstract void failure(Call<T> call, Throwable t);
}
