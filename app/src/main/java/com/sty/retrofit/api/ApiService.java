package com.sty.retrofit.api;

import com.sty.retrofit.base.BaseResult;
import com.sty.retrofit.model.Beauty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tian on 2018/7/3.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST(Urls.GET_BEAUTY_PIC)
    Call<BaseResult<List<Beauty>>> getBeautyPic(@Field("page") int page);
}
