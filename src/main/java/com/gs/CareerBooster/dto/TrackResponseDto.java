package com.gs.CareerBooster.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackResponseDto {
    private Integer id;
    private String title;
    private String description;
    private String area;
}
