package com.pabalvrz.player.service;

import com.pabalvrz.player.model.Statistic;

public interface StatisticService {

    /**
     * Metodo para actualizar las estadisticas de un jugador
     * @param statistic a actualizar
     * @param statistic actual
     *
     * @return statistic actualizada
     */
    Statistic updateStatistic(Statistic statistic, Statistic statisticActual);
}
