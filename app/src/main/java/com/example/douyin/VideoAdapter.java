package com.example.douyin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    List<VideoInfo> dataList;
    public Context context;

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        VideoHolder holder = new VideoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        if(position >= dataList.size()) {
            return;
        }
        VideoInfo videoInfo = dataList.get(position);
        holder.videoView.setVideoPath(videoInfo.feedurl);
        holder.textView.setText(videoInfo.description);
    }

    @Override
    public int getItemCount() {
        return dataList==null? 0: dataList.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        private VideoView videoView;
        private TextView textView;
        private MediaController mediaController;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            if(context == null) {
                //请先为Adapter绑定Context
                return;
            }
            videoView = itemView.findViewById(R.id.videoView);
            mediaController = new MediaController(context);
            videoView.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoView);
            textView = itemView.findViewById(R.id.title);
        }
    }

}
