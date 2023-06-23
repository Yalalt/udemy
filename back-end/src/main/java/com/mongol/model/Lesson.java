package com.mongol.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lesson")
public class Lesson {

    private String name, videoUrl;
    private Integer time;
    private Boolean isFree;

    public Lesson() {
        this("", "", 0, false);
    }

    public Lesson(String name, String videoUrl, Integer time, Boolean isFree) {
        this.name = name;
        this.videoUrl = videoUrl;
        this.time = time;
        this.isFree = isFree;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Boolean isIsFree() {
        return this.isFree;
    }

    public Boolean getIsFree() {
        return this.isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

}
