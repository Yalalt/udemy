package com.mongol.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import util.Util;

@Document(collection = "purchase")
public class Purchase {

    @Id
    private Long id;
    private LocalDate purchaseDate;
    private Long courseid, userid;

    public Purchase() {
        this(LocalDate.now(), 0l, 0l);
    }

    public Purchase(LocalDate purchaseDate, Long courseid, Long userid) {
        this.id = Util.generateUniqueLong();
        this.purchaseDate = purchaseDate;
        this.courseid = courseid;
        this.userid = userid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getCourseid() {
        return this.courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

}
