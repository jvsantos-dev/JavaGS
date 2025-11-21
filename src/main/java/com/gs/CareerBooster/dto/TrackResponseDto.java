package com.gs.CareerBooster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackResponseDto {
    private Integer id;
    private String title;
    private String description;
    private String area;
}
