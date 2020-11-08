package com.masivian.casino.controllers;

import com.masivian.casino.models.Bet;
import com.masivian.casino.models.Roulette;
import com.masivian.casino.models.User;
import com.masivian.casino.services.BetService;
import com.masivian.casino.services.RouletteService;
import com.masivian.casino.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BetController {
    private final BetService betService;
    private final RouletteService rouletteService;
    private final UserService userService;

    @GetMapping("/bets")
    public List<Bet> getAll() {
        List<Bet> bets = new ArrayList<>();
        try {
            bets = betService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bets;
    }

    @PostMapping("/roulettes/{rouletteId}/bet")
    public ResponseEntity<?> makeBet(@PathVariable("rouletteId") Integer rouletteId,
                                     @Valid @RequestBody Bet bet, @RequestHeader("user-id") Integer userId) {
        try {
            String msg = validateBet(bet.getColor(), bet.getNumber());
            if (!msg.isEmpty()) {
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
            bet.setColor(processColor(bet.getColor()));
            if (bet.getMoneyBet() > 10000) {
                return new ResponseEntity<>("Bet limit exceeded ($10.000)", HttpStatus.OK);
            }
            Roulette roulette = rouletteService.findById(rouletteId);
            if (!roulette.isOpen()) {
                return new ResponseEntity<>("The roulette is closed", HttpStatus.OK);
            }
            User user = userService.findById(userId);
            if (user.getMoney() < bet.getMoneyBet()) {
                return new ResponseEntity<>("Insufficient money ", HttpStatus.OK);
            }
            user.setMoney(user.getMoney() - bet.getMoneyBet());
            bet.setUser(user);
            bet.setRoulette(roulette);
            betService.save(bet);
            return new ResponseEntity<>("Bet placed correctly", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while placing the bet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String validateBet(String color, Short number) {
        String message = "";
        if (color != null && number != null) {
            message = "Only color or number bets are allowed, not both";
        } else {
            if (number != null) {
                if (number < 0 || number > 36) {
                    message = "Only numbers between 0 and 36 are allowed";
                }
            } else if (color != null) {
                if (processColor(color) == null) {
                    message = "Wrong color (R or B)";
                }
            } else {
                message = "You must bet on number or color";
            }
        }
        return message;
    }

    private String processColor(String color) {
        if (color == null) {
            return null;
        }
        return switch (color.toUpperCase()) {
            case "R", "RED" -> "R";
            case "B", "BLACK" -> "B";
            default -> null;
        };

    }
}
