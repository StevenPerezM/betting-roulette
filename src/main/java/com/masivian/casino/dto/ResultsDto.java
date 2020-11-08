package com.masivian.casino.dto;

import com.masivian.casino.models.Roulette;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResultsDto {

    @Getter
    @Setter
    private int winningNumber;

    @Getter
    @Setter
    private String color;

    @Getter
    @Setter
    private Roulette roulette;





}
