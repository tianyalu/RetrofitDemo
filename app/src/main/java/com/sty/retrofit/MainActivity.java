package com.sty.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sty.retrofit.adapter.MyRcvAdapter;
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
    private MyRcvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setRecyclerView();
        refresh();
    }

    private void initViews(){
        page = 0;
        dataList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void refresh(){
        if(page == 0){
            page = 1;
        }else{
            page++;
        }
        getData();
    }

    private void getData(){
        Call<BaseResult<List<Beauty>>> call = ApiClient.getApi().getBeautyPic(page);
        call.enqueue(new ApiCallback<BaseResult<List<Beauty>>>(MainActivity.class) {
            @Override
            public void success(Call<BaseResult<List<Beauty>>> call, Response<BaseResult<List<Beauty>>> response, BaseResult<List<Beauty>> result) {
                if(result.isSuccess()){
                    dataList.addAll(result.getBody());
                    setRecyclerView();
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

    private void setRecyclerView(){
        if(adapter == null) {
            adapter = new MyRcvAdapter(this, dataList);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setData(dataList);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refersh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_refresh){
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }
}
