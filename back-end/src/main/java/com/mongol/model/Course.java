package com.mongol.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course")
public class Course {

    private String name, imgUrl;
    private int price, realPrice;
    private LocalDate createdDate;
    private List<Lesson> lessonList = new ArrayList<>();

    public Course() {
        this("", "", 0, 0, LocalDate.now());
    }

    public Course(String name, String imgUrl, int price, int realPrice, LocalDate createdDate) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.realPrice = realPrice;
        this.createdDate = createdDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRealPrice() {
        return this.realPrice;
    }

    public void setRealPrice(int realPrice) {
        this.realPrice = realPrice;
    }

    public List<Lesson> getLessonList() {
        return this.lessonList;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

}
