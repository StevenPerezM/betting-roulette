package com.masivian.casino.services;

import com.masivian.casino.models.Bet;
import com.masivian.casino.repositories.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BetService {
    private final BetRepository betRepository;


    public Bet save(Bet bet) {
        return betRepository.save(bet);
    }

    public List<Bet> getAll() throws Exception {
        return (List<Bet>) betRepository.findAll();
    }

    public void deleteBetsByRoulette(Integer id) {
        betRepository.deleteAllByRouletteId(id);
    }
}
