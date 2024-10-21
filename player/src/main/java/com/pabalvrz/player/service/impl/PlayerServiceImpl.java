package com.pabalvrz.player.service.impl;

import com.pabalvrz.player.model.Player;
import com.pabalvrz.player.model.Statistic;
import com.pabalvrz.player.repository.PlayerRepository;
import com.pabalvrz.player.service.PlayerService;
import com.pabalvrz.player.service.StatisticService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private PlayerRepository playerRepository;

    private final ModelMapper modelMapper;

    public PlayerServiceImpl() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setPropertyCondition(context -> {
            Object sourceValue = context.getSource();
            return sourceValue != null; // Copia solo si no es nulo
        });
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public Player createPlayer(Player player) {
        return this.playerRepository.save(player);
    }

    @Override
    public Page<Player> listAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return this.playerRepository.findAllWithStatistic(pageable);
    }

    @Override
    public Player findById(Long id) {
        return this.playerRepository.findByIdWithStatistic(id).orElseThrow(() -> new RuntimeException("No existe ese jugador"));

    }

    @Override
    public Player updatePlayer(Long id, Player player) {
        Player playerExists = this.playerRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese jugador"));
        modelMapper.map(player, playerExists);
        return this.playerRepository.save(playerExists);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = this.playerRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese jugador"));
        this.playerRepository.delete(player);
    }

    @Override
    public Player updateStatistic(Long id, Statistic statistic) {
        Player playerExists = this.playerRepository.findByIdWithStatistic(id).orElseThrow(() -> new RuntimeException("No existe ese jugador"));
        statistic = this.statisticService.updateStatistic(statistic, playerExists.getStatistic());
        return playerExists;
    }

}
