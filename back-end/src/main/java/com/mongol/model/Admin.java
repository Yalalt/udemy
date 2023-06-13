package com.mongol.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import util.Util;

@Document(collection = "admin")
public class Admin {

    @Id
    private Long id;

    private String name, email, pass, token;

    public Admin() {
        this("", "", "", "");
    }

    public Admin(String name, String email, String pass, String token) {
        this.id = Util.generateUniqueLong();
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.token = token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
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

}
