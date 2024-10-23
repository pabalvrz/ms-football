package com.pabalvrz.team.service.impl;

import com.pabalvrz.team.model.Team;
import com.pabalvrz.team.repository.TeamRepository;
import com.pabalvrz.team.service.TeamService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    private final ModelMapper modelMapper;

    public TeamServiceImpl(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setPropertyCondition(context -> {
            Object sourceValue = context.getSource();
            return sourceValue != null; // Copia solo si no es nulo
        });
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @Override
    public Team createPlayer(Team team) {
        return this.teamRepository.save(team);
    }

    @Override
    public Page<Team> listAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return this.teamRepository.findAll(pageable);
    }

    @Override
    public Team findById(Long id) {
        return this.teamRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese jugador"));

    }

    @Override
    public Team updatePlayer(Long id, Team team) {
        Team teamExists = this.teamRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese jugador"));
        modelMapper.map(team, teamExists);
        return this.teamRepository.save(teamExists);
    }

    @Override
    public void deletePlayer(Long id) {
        Team player = this.teamRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese jugador"));
        this.teamRepository.delete(player);
    }
}
