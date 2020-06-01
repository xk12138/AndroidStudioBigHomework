package com.example.douyin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
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

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        // 获取intent传递的参数，并解析
        Intent intent = getIntent();
        String list = intent.getStringExtra("dataList");
        String id = intent.getStringExtra("currentId");
        Gson gson = new Gson();
        List<LinkedTreeMap> maps = gson.fromJson(list, List.class);
        dataList = new LinkedList<>();
        int state = 0;
        if(maps != null) {
            for(LinkedTreeMap map: maps) {
                VideoInfo videoInfo = new VideoInfo(map);
                if(id.equals(videoInfo._id)) {
                    state = 1;
                }
                if(state == 1) {
                    dataList.add(videoInfo);
                }
            }
        }

        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setOrientation(RecyclerView.HORIZONTAL);
        videoAdapter = new VideoAdapter();
        videoAdapter.context = MyViewPager2.this;
        videoAdapter.dataList = this.dataList;
        viewPager2.setAdapter(videoAdapter);
        videoAdapter.notifyDataSetChanged();
    }
}
