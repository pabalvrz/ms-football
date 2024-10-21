package com.pabalvrz.player.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pabalvrz.player.model.Player;
import com.pabalvrz.player.model.Statistic;
import com.pabalvrz.player.model.enums.Foot;
import com.pabalvrz.player.model.enums.Position;
import com.pabalvrz.player.model.enums.SpecificPosition;
import com.pabalvrz.player.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Player player;
    private Player player2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Statistic statistic = new Statistic(1L, 15, 10, 30, 2700, 5, 1);
        player = new Player(1L, "Lionel", "Messi", new Date(87, 5, 24), 36, 1.70
                , 72.0, Position.STRIKER, SpecificPosition.ST, Foot.LEFT, 10,
                statistic);
        player2 = new Player(2L, "Cristiano", "Ronaldo", new Date(1985 - 1900, 1, 5),
                39, 1.87, 83.0, Position.STRIKER, SpecificPosition.ST, Foot.RIGHT,
                7, new Statistic(2L, 850, 230, 1100, 52000, 60, 5));

    }

    @Test
    public void createPlayerTest() throws Exception {
        // Simulamos el comportamiento del servicio
        when(playerService.createPlayer(any(Player.class))).thenReturn(player);

        String jsonPlayer = objectMapper.writeValueAsString(player);
        MvcResult result = mockMvc.perform(post("/api/player")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPlayer))
                .andExpect(status().isOk()) // Verifica que la respuesta es 200 OK
                .andReturn();


        // Obtiene y verifica el contenido de la respuesta
        String jsonResponse = result.getResponse().getContentAsString();
        // respuesta
        assertEquals(jsonPlayer, jsonResponse); // Compara el JSON esperado con el JSON de respuesta
    }

    @Test
    public void getAllPlayerTest() throws Exception {
        Page<Player> playerPage = new PageImpl<>(List.of(player, player2));
        when(playerService.listAllPaginated(anyInt(), anyInt())).thenReturn(playerPage);

        MvcResult result = mockMvc.perform(get("/api/player")
                .param("page", "0") // Página 0
                .param("size", "10") // Tamaño 10
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Obtiene y verifica el contenido de la respuesta
        String jsonResponse = result.getResponse().getContentAsString();

        // Asegúrate de que la respuesta contiene la lista de jugadores
        String expectedJsonResponse = objectMapper.writeValueAsString(playerPage);
        assertEquals(expectedJsonResponse, jsonResponse); // Compara el JSON esperado con el JSON de respuesta
    }

    @Test
    public void getPlayerTest() throws Exception {
        when(playerService.findById(anyLong())).thenReturn(player);

        MvcResult result = mockMvc.perform(get("/api/player/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Verifica que la respuesta es 200 OK
                .andReturn();

        String jsonPlayer = objectMapper.writeValueAsString(player);
        String jsonResponse = result.getResponse().getContentAsString();
        assertEquals(jsonPlayer, jsonResponse);
    }

    @Test
    public void updatePlayerTest() throws Exception{
        Player updatedPlayer = new Player(1L, "Lionel", "Messi", new Date(87, 5, 24), 36, 1.70
                , 72.0, Position.STRIKER, SpecificPosition.RW, Foot.LEFT, 10,
                new Statistic(1L, 15, 10, 30, 2700, 5, 1));

        when(playerService.updatePlayer(anyLong(), any(Player.class))).thenReturn(updatedPlayer);

        String jsonUpdatedPlayer = objectMapper.writeValueAsString(updatedPlayer);

        MvcResult result = mockMvc.perform(patch("/api/player/{id}", 1L) // Usar un ID de jugador específico
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdatedPlayer)) // Contenido JSON
                .andExpect(status().isOk()) // Verifica que la respuesta es 200 OK
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(updatedPlayer);
        assertEquals(expectedJsonResponse, jsonResponse);
    }

    @Test
    public void deletePlayerTest() throws Exception{
        Long playerId = 1L;

        doNothing().when(playerService).deletePlayer(anyLong());

        MvcResult result = mockMvc.perform(delete("/api/player/{id}", playerId) // Usar un ID de jugador específico
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Verifica que la respuesta es 200 OK
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        String expectedResponse = "Jugador eliminado con éxito";
        assertEquals(expectedResponse, jsonResponse);
    }

    @Test
    public void updateStatisticTest() throws Exception{
        when(playerService.updateStatistic(anyLong(), any(Statistic.class))).thenReturn(player);
        Statistic statisticUpdate = new Statistic(1L, 20, 15, 35, 3000, 6, 2);

        String jsonStatistic = objectMapper.writeValueAsString(statisticUpdate);

        // Realiza la solicitud PATCH al endpoint
        MvcResult result = mockMvc.perform(patch("/api/player/{id}/statistic", 1L) // Usar un ID de jugador específico
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStatistic)) // Contenido JSON
                .andExpect(status().isOk()) // Verifica que la respuesta es 200 OK
                .andReturn();


        String jsonResponse = result.getResponse().getContentAsString();

        String expectedJsonResponse = objectMapper.writeValueAsString(player);
        assertEquals(expectedJsonResponse, jsonResponse); // Compara el JSON esperado con el JSON de respuesta

    }
}
