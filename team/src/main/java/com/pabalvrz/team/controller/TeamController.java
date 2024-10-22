package com.pabalvrz.team.controller;

import com.pabalvrz.team.model.Team;
import com.pabalvrz.team.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Team", description = "Controlador encargado de los equipos del sistema")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Operation(
            summary = "Create team",
            description = "Create team giving all the information needed")
    @PostMapping
    private ResponseEntity<Team> createPlayer(@RequestBody Team player){
        return new ResponseEntity<>(this.teamService.createPlayer(player), HttpStatus.OK);
    }


    @Operation(
            summary = "Retrieve all teams",
            description = "Return the info of all the teams on the system")
    @GetMapping
    private ResponseEntity<Page<Team>> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(this.teamService.listAllPaginated(page, size),HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve an specific team",
            description = "Return the info of the team giving an id")
    @GetMapping("/{id}")
    private ResponseEntity<Team> getPlayer(@PathVariable Long id){
        return new ResponseEntity<>(this.teamService.findById(id),HttpStatus.OK);
    }

    @Operation(
            summary = "Update team",
            description = "Update team giving the info you want to")
    @PatchMapping("/{id}")
    private ResponseEntity<Team> updatePlayer(@PathVariable Long id, @RequestBody Team player){
        return new ResponseEntity<>(this.teamService.updatePlayer(id,player), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete team",
            description = "Delete team")
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deletePlayer(@PathVariable Long id){
        this.teamService.deletePlayer(id);
        return new ResponseEntity<>("Equipo eliminado con éxito", HttpStatus.OK);
    }
}
