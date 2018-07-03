package com.sty.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sty.retrofit.api.ApiCallback;
import com.sty.retrofit.api.ApiClient;
import com.sty.retrofit.base.BaseResult;
import com.sty.retrofit.model.Beauty;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int page;
    private List<Beauty> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getData();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recycler_view);
        page = 1;
        dataList = new ArrayList<>();
    }

    private void getData(){
        Call<BaseResult<List<Beauty>>> call = ApiClient.getApi().getBeautyPic(page);
        call.enqueue(new ApiCallback<BaseResult<List<Beauty>>>(MainActivity.class) {
            @Override
            public void success(Call<BaseResult<List<Beauty>>> call, Response<BaseResult<List<Beauty>>> response, BaseResult<List<Beauty>> result) {
                if(result.isSuccess()){
                    dataList.addAll(result.getBody());
                    Log.i("sty", "dataList :" + dataList.size());
                    Toast.makeText(MainActivity.this, "加载数据成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void error(Call<BaseResult<List<Beauty>>> call, Response<BaseResult<List<Beauty>>> response) {
                Toast.makeText(MainActivity.this, "获取数据错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(Call<BaseResult<List<Beauty>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "获取数据失败： " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
