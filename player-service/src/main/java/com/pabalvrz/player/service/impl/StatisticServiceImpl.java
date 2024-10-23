package com.pabalvrz.player.service.impl;

import com.pabalvrz.player.model.Statistic;
import com.pabalvrz.player.repository.StatisticRepository;
import com.pabalvrz.player.service.StatisticService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    private final ModelMapper modelMapper;

    public StatisticServiceImpl() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setPropertyCondition(context -> {
            Object sourceValue = context.getSource();
            return sourceValue != null; // Copia solo si no es nulo
        });
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public Statistic updateStatistic(Statistic statistic, Statistic statisticActual) {
        modelMapper.map(statistic, statisticActual);
        return this.statisticRepository.save(statisticActual);
    }
}
