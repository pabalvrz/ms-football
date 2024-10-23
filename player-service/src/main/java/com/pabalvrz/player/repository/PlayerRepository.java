package com.pabalvrz.player.repository;

import com.pabalvrz.player.model.Player;
import com.pabalvrz.player.model.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("SELECT p FROM Player p LEFT JOIN FETCH p.statistic WHERE p.id = :id")
    Optional<Player> findByIdWithStatistic(@Param("id") Long id);

    @Query(value = "SELECT p FROM Player p LEFT JOIN FETCH p.statistic",
            countQuery = "SELECT COUNT(p) FROM Player p")
    Page<Player> findAllWithStatistic(Pageable pageable);

}
