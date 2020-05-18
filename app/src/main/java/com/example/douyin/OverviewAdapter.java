package com.example.douyin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Parcelable;
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
import com.google.gson.Gson;

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
        if(position >= dataList.size()) {
            return;
        }
        VideoInfo videoInfo = dataList.get(position);
        holder.id = videoInfo._id;
        holder.description.setText(videoInfo.description);

        //使用视频的第一帧作为封面
        Bitmap fistFrame, middleFrame;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(videoInfo.feedurl);

        fistFrame = retriever.getFrameAtTime();
        middleFrame = retriever.getFrameAtTime(1000, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);

        Glide.with(context).load(fistFrame).into(holder.imageView);
        Glide.with(context).load(middleFrame).into(holder.imageView2);

        open(holder);
    }

    private void open(OverviewHolder holder) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyViewPager2.class);
                Gson gson = new Gson();
                String list = gson.toJson(dataList);
                intent.putExtra("dataList", list);
                intent.putExtra("currentId", holder.id);
                context.startActivity(intent);
            }
        };
        holder.imageView.setOnClickListener(listener);
        holder.imageView2.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return dataList==null? 0: dataList.size();
    }

    public static class OverviewHolder extends RecyclerView.ViewHolder {

        public String id;
        public TextView description;
        public ImageView imageView;
        public ImageView imageView2;

        public OverviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView2 = itemView.findViewById(R.id.imageView2);
            description = itemView.findViewById(R.id.id);
        }
    }

    public void setDataList(List<VideoInfo> dataList) {
        this.dataList = dataList;
    }

}
