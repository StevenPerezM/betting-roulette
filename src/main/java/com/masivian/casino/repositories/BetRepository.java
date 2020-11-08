package com.masivian.casino.repositories;

import com.masivian.casino.models.Bet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface BetRepository extends CrudRepository<Bet, Integer> {

    @Query(nativeQuery = true, value = "DELETE FROM bets WHERE id_roulette = ?1")
    @Modifying
    @Transactional
    void deleteAllByRouletteId(Integer id);
}
