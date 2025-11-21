package com.gs.CareerBooster.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserModel {
    private Integer id;
    private String password;
    private String name;
    private String email;
    private String username;

    @JsonProperty("area_interest")
    private String areaInterest;
}

