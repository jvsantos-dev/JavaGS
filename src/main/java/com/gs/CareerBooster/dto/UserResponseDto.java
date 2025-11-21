package com.gs.CareerBooster.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String area_interest;
}
