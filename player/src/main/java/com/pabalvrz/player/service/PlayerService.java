package com.pabalvrz.player.service;

import com.pabalvrz.player.model.Player;
import com.pabalvrz.player.model.Statistic;
import org.springframework.data.domain.Page;

public interface PlayerService {
    Player createPlayer(Player player);

    Page<Player> listAllPaginated(int page, int size);

    Player findById(Long id);

    /**
     * Metodo para actualizar un jugador existente en base de datos proporcionando su id
     *
     * @param id
     * @param player
     * @return player actualizado
     */
    Player updatePlayer(Long id, Player player);

    /**
     * Metodo para eliminar un jugador existente en base de datos proporcionando su id
     * @param id
     * @return
     */
    void deletePlayer(Long id);

    /**
     * Metodo para actualizar las estadisticas de un jugador
     * @param id
     * @param statistic
     * @return jugador con estadisticas actualizadas
     */
    Player updateStatistic(Long id, Statistic statistic);
}
