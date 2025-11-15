package com.gs.CareerBooster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTrackDto {
    @NotNull(message = "ID é obrigatório")
    private Integer id;

    @NotBlank(message = "Título é obrigatório")
    private String title;

    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    @NotBlank(message = "Área é obrigatória")
    private String area;
}
