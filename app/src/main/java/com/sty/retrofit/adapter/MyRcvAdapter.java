package com.sty.retrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sty.retrofit.R;
import com.sty.retrofit.model.Beauty;

import java.util.List;

/**
 * Created by tian on 2018/7/3.
 */

public class MyRcvAdapter extends RecyclerView.Adapter<MyRcvAdapter.MyViewHolder>{
    //private List<Beauty> dataList;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_beauty);
        }
    }
}
