package com.masivian.casino.services;

import com.masivian.casino.models.Roulette;
import com.masivian.casino.repositories.RouletteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RouletteService {
    private final RouletteRepository rouletteRepository;

    public List<Roulette> getAll() throws Exception {
        return (List<Roulette>) rouletteRepository.findAll();
    }

    public Roulette save(Roulette roulette) throws Exception {
        return rouletteRepository.save(roulette);
    }

    public void changeRouletteState(Roulette roulette, boolean state) throws Exception {
        roulette.setOpen(state);
        rouletteRepository.save(roulette);
    }

    public Roulette findById(Integer id) throws Exception {
        return rouletteRepository.findById(id).get();
    }
}
