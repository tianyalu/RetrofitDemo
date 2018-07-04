package com.sty.retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sty.retrofit.R;
import com.sty.retrofit.model.Beauty;

import java.util.List;

/**
 * Created by tian on 2018/7/3.
 */

public class MyRcvAdapter extends RecyclerView.Adapter<MyRcvAdapter.MyViewHolder>{
    private List<Beauty> dataList;
    private Context mContext;

    public MyRcvAdapter(Context context, List<Beauty> dataList){
        this.mContext = context;
        this.dataList = dataList;
    }

    public void setData(List<Beauty> list){
        this.dataList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_beauty_img, parent, false);
        MyRcvAdapter.MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.mImageView.setTag(dataList.get(position).getUrl());
        Glide.with(mContext)
                .load(dataList.get(position).getUrl())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_beauty);
        }
    }
}
