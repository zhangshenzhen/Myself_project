package com.draghelper.bean;

import android.graphics.Bitmap;

public class Item_Bean {
    private Bitmap picture;
    private String type;

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String content;
    private String time;
    public Item_Bean(Bitmap picture, String type, String content, String time){
     this.picture = picture;
     this.type = type;
     this.content = content;
     this.time = time;
    }

    @Override
    public String toString() {
        return "Item_Bean{" +
                "picture=" + picture +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
