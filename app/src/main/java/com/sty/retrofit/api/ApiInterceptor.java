package com.sty.retrofit.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tian on 2018/7/3.
 */

public class ApiInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        try{
            Request authorised = originalRequest.newBuilder()
                    .header("Authorization", "Your Authorization Token")
                    .header("platform", "Android-" + "MI")
                    .header("owner", "Your Customer Info")
                    .build();
            return chain.proceed(authorised);
        }catch (Exception e){
            e.printStackTrace();
        }
        return chain.proceed(originalRequest);
    }
}
