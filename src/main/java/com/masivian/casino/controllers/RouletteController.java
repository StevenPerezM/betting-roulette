package com.masivian.casino.controllers;

import com.masivian.casino.dto.ResultsDto;
import com.masivian.casino.dto.RouletteDto;
import com.masivian.casino.models.Bet;
import com.masivian.casino.models.Roulette;
import com.masivian.casino.services.BetService;
import com.masivian.casino.services.RouletteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/roulettes")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RouletteController {
    private final RouletteService rouletteService;
    private final BetService betService;

    @GetMapping
    public List<Roulette> getAll() {
        List<Roulette> roulettes = new ArrayList<>();
        try {
            roulettes = rouletteService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roulettes;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Roulette roulette) {
        try {
            roulette.setOpen(false);
            Roulette newRoulette = rouletteService.save(roulette);
            return new ResponseEntity<>(new RouletteDto(newRoulette), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while placing the bet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{rouletteId}/open")
    public ResponseEntity<?> openRoulette(@PathVariable("rouletteId") Integer rouletteId) {
        try {
            Roulette roulette = rouletteService.findById(rouletteId);
            betService.deleteBetsByRoulette(roulette.getIdRoulette());
            roulette.setBetsPlaced(new HashSet<>());
            rouletteService.changeRouletteState(roulette, true);
            return new ResponseEntity<>("Roulette can now receive bets", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while placing the bet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{rouletteId}/close")
    public ResponseEntity<?> closeRoulette(@PathVariable("rouletteId") Integer rouletteId) {
        try {
            Roulette roulette = rouletteService.findById(rouletteId);
            if (!roulette.isOpen()) {
                return new ResponseEntity<>("Bets have already been closed", HttpStatus.OK);
            }
            int number = generateWinningNumber();
            String color = (number % 2 == 0) ? "R" : "B";

            payBets(roulette.getBetsPlaced(), color, number);
            rouletteService.changeRouletteState(roulette, false);
            return new ResponseEntity<>(new ResultsDto(number, color, roulette), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while placing the bet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void payBets(Set<Bet> bets, String color, int number) {
        for (Bet bet : bets) {
            if (color.equals(bet.getColor())) {
                bet.getUser().setMoney(bet.getUser().getMoney() + bet.getMoneyBet() * 1.8);
            } else if (bet.getNumber() == number) {
                bet.getUser().setMoney(bet.getUser().getMoney() + bet.getMoneyBet() * 5);
            }
        }
    }

    private Integer generateWinningNumber() {
        return (int) (Math.random() * 37);
    }
}
