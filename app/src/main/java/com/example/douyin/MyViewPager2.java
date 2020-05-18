package com.example.douyin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.LinkedList;
import java.util.List;

public class MyViewPager2 extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private VideoAdapter videoAdapter;

    private List<VideoInfo> dataList;
    private int currentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        Intent intent = getIntent();
        String list = intent.getStringExtra("dataList");
        String id = intent.getStringExtra("currentId");
        Gson gson = new Gson();
        List<LinkedTreeMap> maps = gson.fromJson(list, List.class);
        dataList = new LinkedList<>();
        if(maps != null) {
            for(LinkedTreeMap map: maps) {
                VideoInfo videoInfo = new VideoInfo(map);
                dataList.add(videoInfo);
                if(id.equals(videoInfo._id)) {

                }
            }
        }

        viewPager2 = findViewById(R.id.viewPager2);
        videoAdapter = new VideoAdapter();
        videoAdapter.dataList = this.dataList;
        videoAdapter.notifyDataSetChanged();
    }
}
