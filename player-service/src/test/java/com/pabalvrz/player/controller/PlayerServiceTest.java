package com.pabalvrz.player.controller;


import com.pabalvrz.player.model.Player;
import com.pabalvrz.player.model.Statistic;
import com.pabalvrz.player.model.enums.Foot;
import com.pabalvrz.player.model.enums.Position;
import com.pabalvrz.player.model.enums.SpecificPosition;
import com.pabalvrz.player.repository.PlayerRepository;
import com.pabalvrz.player.service.StatisticService;
import com.pabalvrz.player.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private StatisticService statisticService;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player player1;
    private Player player2;
    private Statistic statistic1;
    private Statistic statistic2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        player1 = new Player(1L, "Lionel", "Messi", new Date(1987 - 1900, 5, 24), 37, 1.70, 72.0,
                Position.STRIKER, SpecificPosition.ST, Foot.LEFT, 10,
                new Statistic(1L, 800, 300, 1000, 50000, 70, 3));

        player2 = new Player(2L, "Cristiano", "Ronaldo", new Date(1985 - 1900, 1, 5), 39, 1.87, 83.0,
                Position.STRIKER, SpecificPosition.LW, Foot.RIGHT, 7,
                new Statistic(2L, 850, 230, 1100, 52000, 60, 5));

        statistic1 = new Statistic(1L, 800, 300, 1000, 50000, 70, 3);
        statistic2 = new Statistic(2L, 850, 230, 1100, 52000, 60, 5);
    }

    @Test
    public void testCreatePlayer() {
        // Configuramos el comportamiento del mock
        when(playerRepository.save(player1)).thenReturn(player1);

        // Llamamos al metodo
        Player createdPlayer = playerService.createPlayer(player1);

        // Verificamos que el repositorio fue llamado y que se devolvió el jugador creado
        verify(playerRepository, times(1)).save(player1);
        assertEquals(player1, createdPlayer);
    }

    @Test
    public void testListAllPaginated() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Player> playerPage = new PageImpl<>(Arrays.asList(player1, player2), pageable, 2);

        // Configuramos el mock
        when(playerRepository.findAllWithStatistic(pageable)).thenReturn(playerPage);

        // Llamamos al metodo
        Page<Player> resultPage = playerService.listAllPaginated(0, 10);

        // Verificamos los resultados
        assertEquals(2, resultPage.getTotalElements());
        assertEquals(player1, resultPage.getContent().get(0));
        assertEquals(player2, resultPage.getContent().get(1));
    }

    @Test
    public void testFindById() {
        // Configuramos el mock para devolver un jugador con estadísticas
        when(playerRepository.findByIdWithStatistic(1L)).thenReturn(Optional.of(player1));

        // Llamamos al metodo
        Player foundPlayer = playerService.findById(1L);

        // Verificamos los resultados
        assertEquals(player1, foundPlayer);
    }

    @Test
    public void testUpdatePlayer() {
        // Configuramos el mock para devolver un jugador existente
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        when(playerRepository.save(player1)).thenReturn(player1);

        // Llamamos al metodo
        Player updatedPlayer = playerService.updatePlayer(1L, player2); // Actualiza player1 con los datos de player2

        // Verificamos que el mapper haya copiado correctamente los datos
        verify(playerRepository, times(1)).save(player1);
        assertEquals(player2.getLastName(), updatedPlayer.getLastName());
        assertEquals(player2.getHeight(), updatedPlayer.getHeight());
    }

    @Test
    public void testDeletePlayer() {
        // Configuramos el mock para devolver un jugador existente
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));

        // Llamamos al método
        playerService.deletePlayer(1L);

        // Verificamos que se llamó al método de borrado
        verify(playerRepository, times(1)).delete(player1);
    }

    @Test
    public void testUpdateStatistic() {
        // Configuramos el mock para devolver un jugador con estadísticas
        when(playerRepository.findByIdWithStatistic(1L)).thenReturn(Optional.of(player1));
        when(statisticService.updateStatistic(statistic2, statistic1)).thenReturn(statistic1);

        // Llamamos al método
        Player updatedPlayer = playerService.updateStatistic(1L, statistic2);

        // Verificamos que las estadísticas se hayan actualizado
        verify(statisticService, times(1)).updateStatistic(statistic2, statistic1);
        assertEquals(statistic1, updatedPlayer.getStatistic());
    }
}
