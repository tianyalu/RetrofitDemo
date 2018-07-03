package com.sty.retrofit.base;

import java.util.ArrayList;

/**
 * Created by tian on 2018/7/3.
 */

public class BaseResult<T> {
    private int code;
    private String msg;
    private T data;
    private int count;
    private String result;
    private String appKey;
    private String errGid;

    public boolean isSuccess(){
        if(code == 200 || msg.contains("成功")){
            return true;
        }
        return false;
    }

    public static <T> T createInstance(Class<T> cls){
        T obj = null;
        try{
            obj = cls.newInstance();
        }catch (Exception e){
            obj = null;
        }
        return obj;
    }

    public T getBody(Class<T> cls){
        if(data == null){
            try {
                data = createInstance(cls);
            }catch (Exception e){
                return null;
            }
        }
        return data;
    }

    public T getBody(){
        if(data == null){
            try{
                data = (T) new ArrayList<>();
            }catch (Exception e){
                return null;
            }
        }
        return data;
    }

    public void setBody(T data){
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return getBody();
    }

    public void setData(T data) {
        this.setBody(data);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getErrGid() {
        return errGid;
    }

    public void setErrGid(String errGid) {
        this.errGid = errGid;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code = " + code +
                ", msg = " + msg + '\'' +
                ", data = " + data +
                ", count = " + count +
                ", result = " + result + '\'' +
                ", appKey = " + appKey + '\'' +
                "}";
    }
}
