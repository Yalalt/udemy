package com.mongol.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import util.Util;

@Document(collection = "lesson")
public class Lesson {
    @Id
    private Long id;
    private String name, videoUrl;
    private Integer time;
    private Long courseid;
    private Boolean isFree;

    public Lesson() {
        this("", "", 0, 0l, false);
    }


    public Lesson(String name, String videoUrl, Integer time, Long courseid, Boolean isFree) {
        this.id = Util.generateUniqueLong();
        this.name = name;
        this.videoUrl = videoUrl;
        this.time = time;
        this.courseid = courseid;
        this.isFree = isFree;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCourseid() {
        return this.courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
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
