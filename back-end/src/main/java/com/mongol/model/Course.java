package com.mongol.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import util.Util;

@Document(collection = "course")
public class Course {
    
    @Id
    private Long id;
    private String name, imgUrl;
    private int price, realPrice;
    private Long userid;
    private LocalDate createdDate;


    // public Course() {
    //     this("", "", 0, 0, 0, LocalDate.now());
    // }



    public Course(String name, String imgUrl, int price, int realPrice, Long userid, LocalDate createdDate) {
        this.id = Util.generateUniqueLong();
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.realPrice = realPrice;
        this.userid = userid;
        this.createdDate = createdDate;
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

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    

}
