package com.kuyin.lover.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kuyin.lover.R;
import com.kuyin.lover.model.ImageModel;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.MyViewHolder> {

    private Context context;
    private List<ImageModel.DataBean> imageModels;

    public ImageListAdapter(Context context, List<ImageModel.DataBean> imageModels) {
        this.context = context;
        this.imageModels = imageModels;
    }

    public void setImageModels(List<ImageModel.DataBean> imageModels) {
        this.imageModels = imageModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String path = imageModels.get(position).getUrl();

        Glide.with(context).load(path)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (imageModels != null) {
            return this.imageModels.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
