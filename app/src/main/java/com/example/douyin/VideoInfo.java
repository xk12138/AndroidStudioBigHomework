package com.example.douyin;

import android.content.Intent;

import com.google.gson.internal.LinkedTreeMap;

public class VideoInfo {

    public String _id;
    public String feedurl;
    public String nickname;
    public String description;
    public Integer likecount;
    public String avatar;

    public VideoInfo() {}
    public VideoInfo(LinkedTreeMap map) {
        this._id = (String)map.get("_id");
        this.nickname = (String)map.get("nickname");
        this.avatar = (String)map.get("avatar");
        this.description = (String)map.get("description");
        this.likecount = ((Double)map.get("likecount")).intValue();
        this.feedurl = (String)map.get("feedurl");
    }

}
