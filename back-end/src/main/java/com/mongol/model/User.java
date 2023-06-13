package com.mongol.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import util.Util;

@Document(collection = "user")
public class User {
    @Id
    private Long id;
    private String name, email, pass, token, salt;
    private Boolean isTeacher;

    public User() {
        this("", "", "", "", false);
    }


    public User(String name, String email, String pass, String token, Boolean isTeacher) {
        this.id = Util.generateUniqueLong();
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.token = token;
        this.isTeacher = isTeacher;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean isIsTeacher() {
        return this.isTeacher;
    }

    public Boolean getIsTeacher() {
        return this.isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }


    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
