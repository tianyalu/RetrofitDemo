package com.sty.retrofit.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sty.retrofit.app.MyApplication;

import java.io.File;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tian on 2018/7/3.
 */

public class ApiClient {
    private static ApiService api;
    private static OkHttpClient mOkHttpClient;
    private static ApiInterceptor interceptor = new ApiInterceptor();

    public static void init(){
        Context context = MyApplication.getmApp();

        //缓存目录
        File cacheDirectory = new File(context.getCacheDir().getAbsolutePath(), "HttpCache");
        Cache cache = new Cache(cacheDirectory, 20 * 1024 * 1024);

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) //设定30秒超时
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .connectionPool(new ConnectionPool(10, 300, TimeUnit.MILLISECONDS))
                //.addNetworkInterceptor(new CookiesInterceptor(context)) //设置拦截器，以用于自定义Cookies
                //.cache(cache)  //设置缓存目录
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL) //配置服务器路径
                .addConverterFactory(GsonConverterFactory.create(createCusGson())) //配置转化库，默认为Gson
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //配置回调库，采用RxJava
                .client(mOkHttpClient)  //设置OkHttpClient为网络客户端
                .build();

        api = retrofit.create(ApiService.class);
    }

    /**
     * 创建一个自定义的 Gson,在解析 int,double,long,float 的数据时,
     * 若解析错误(返回值为 null 或者 "")则设为默认值 0
     * @return
     */
    private static Gson createCusGson(){
        return new GsonBuilder()
                .registerTypeAdapter(int.class, new JsonDeserializer<Integer>() {

                    @Override
                    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsInt();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }
                })
                .registerTypeAdapter(double.class, new JsonDeserializer<Double>() {
                    @Override
                    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsDouble();
                        }catch (Exception e){
                            e.printStackTrace();
                            return 0d;
                        }
                    }
                })
                .registerTypeAdapter(float.class, new JsonDeserializer<Float>() {
                    @Override
                    public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsFloat();
                        }catch (Exception e){
                            e.printStackTrace();
                            return 0f;
                        }
                    }
                })
                .registerTypeAdapter(long.class, new JsonDeserializer<Long>() {
                    @Override
                    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsLong();
                        }catch (Exception e){
                            e.printStackTrace();
                            return 0L;
                        }
                    }
                })
                .create();
    }

    public static ApiService getApi(){
        return api;
    }
}
