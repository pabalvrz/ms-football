package com.pabalvrz.player.controller;

import com.pabalvrz.player.model.Player;
import com.pabalvrz.player.model.Statistic;
import com.pabalvrz.player.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@Tag(name = "Player", description = "Controlador encargado de los jugadores del sistema")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(
            summary = "Create player",
            description = "Create player giving all the information needed")
    @PostMapping
    private ResponseEntity<Player> createPlayer(@RequestBody Player player){
        return new ResponseEntity<>(this.playerService.createPlayer(player), HttpStatus.OK);
    }


    @Operation(
            summary = "Retrieve all players",
            description = "Return the info of all the players on the system")
    @GetMapping
    private ResponseEntity<Page<Player>> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(this.playerService.listAllPaginated(page, size),HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve an specific player",
            description = "Return the info of the player giving an id")
    @GetMapping("/{id}")
    private ResponseEntity<Player> getPlayer(@PathVariable Long id){
        return new ResponseEntity<>(this.playerService.findById(id),HttpStatus.OK);
    }

    @Operation(
            summary = "Update player",
            description = "Update player giving the info you want to")
    @PatchMapping("/{id}")
    private ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player){
        return new ResponseEntity<>(this.playerService.updatePlayer(id,player), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete player",
            description = "Delete player")
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deletePlayer(@PathVariable Long id){
        this.playerService.deletePlayer(id);
        return new ResponseEntity<>("Jugador eliminado con éxito", HttpStatus.OK);
    }


    @Operation(
            summary = "Update player statistics",
            description = "Update player giving the player id and the statistic")
    @PatchMapping("/{id}/statistic")
    private ResponseEntity<Player> updateStatistic(@PathVariable Long id, @RequestBody Statistic statistic){
        return new ResponseEntity<>(this.playerService.updateStatistic(id,statistic), HttpStatus.OK);
    }
}
