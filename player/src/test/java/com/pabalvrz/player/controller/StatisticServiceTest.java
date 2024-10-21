package com.pabalvrz.player.controller;

import com.pabalvrz.player.model.Statistic;
import com.pabalvrz.player.repository.StatisticRepository;
import com.pabalvrz.player.service.impl.StatisticServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class StatisticServiceTest {

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Mock
    private StatisticRepository statisticRepository;

    private Statistic statistic;
    private Statistic existingStatistic;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configura los objetos de prueba
        existingStatistic = new Statistic(1L, 10, 5, 25, 2000, 3, 1);
        statistic = new Statistic(1L, 15, 10, 30, 2500, 4, 2);
    }

    @Test
    public void updateStatisticTest() {

        when(statisticRepository.save(any(Statistic.class))).thenReturn(existingStatistic);

        Statistic updatedStatistic = statisticService.updateStatistic(statistic, existingStatistic);

        verify(statisticRepository).save(existingStatistic);

        // Verifica que el objeto actualizado no es nulo y que es el mismo que se devuelve
        assertNotNull(updatedStatistic);
        assertEquals(existingStatistic, updatedStatistic); // Aquí podrías verificar propiedades específicas si lo deseas
    }
}
