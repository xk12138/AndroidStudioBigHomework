package com.example.douyin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    List<VideoInfo> dataList;

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

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textView = itemView.findViewById(R.id.title);
        }
    }

}
