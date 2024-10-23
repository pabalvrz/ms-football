package com.pabalvrz.team.service;

import com.pabalvrz.team.model.Team;
import org.springframework.data.domain.Page;

public interface TeamService {
    Team createPlayer(Team team);

    Page<Team> listAllPaginated(int page, int size);

    Team findById(Long id);

    Team updatePlayer(Long id, Team team);

    void deletePlayer(Long id);
}
