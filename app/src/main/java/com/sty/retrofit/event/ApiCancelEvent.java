package com.sty.retrofit.event;

import android.support.annotation.NonNull;

/**
 * Created by tian on 2018/7/3.
 */

public class ApiCancelEvent {
    private Class<?> clz;

    public ApiCancelEvent(@NonNull Class<?> clz){
        this.clz = clz;
    }

    public Class<?> getClz(){
        return clz;
    }

    public void setClz(Class<?> clz){
        this.clz = clz;
    }
}
