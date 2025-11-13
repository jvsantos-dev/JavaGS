package com.gs.CareerBooster.model;

import lombok.Data;

@Data
public class UserModel {
    private Integer id;
    private String password;
    private String name;
    private String email;
    private String username;
    private String area_interest;

    public UserModel() {}

    public UserModel(Integer id, String password, String name, String email,
                     String username, String area_interest){
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.username = username;
        this.area_interest = area_interest;
    }

}
