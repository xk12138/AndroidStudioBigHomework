package com.example.douyin;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.OverviewHolder> {

    private List<VideoInfo> dataList;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OverviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.overview, parent, false);
        OverviewHolder holder = new OverviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OverviewHolder holder, int position) {
        if(context == null)
            return;
        VideoInfo videoInfo = dataList.get(position);
        holder.id = videoInfo._id;
        holder.description.setText(videoInfo.description);

        //使用视频的第一帧作为封面
        RequestOptions requestOptions = RequestOptions.frameOf(0);
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
        requestOptions.transform(new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                return toTransform;
            }

            @Override
            public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
                try {
                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        Glide.with(context).load(videoInfo.feedurl).apply(requestOptions).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList==null? 0: dataList.size();
    }

    public static class OverviewHolder extends RecyclerView.ViewHolder {

        public String id;
        public TextView description;
        public ImageView imageView;

        public OverviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            description = itemView.findViewById(R.id.id);
        }
    }

    public void setDataList(List<VideoInfo> dataList) {
        this.dataList = dataList;
    }

}
