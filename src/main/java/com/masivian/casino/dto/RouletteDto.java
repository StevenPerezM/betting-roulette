package com.masivian.casino.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.masivian.casino.models.Roulette;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class RouletteDto {
    @Getter
    @Setter
    @JsonIgnoreProperties({"name", "open", "betsPlaced"})
    private Roulette roulette;



}
