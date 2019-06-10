package org.gx.chronorun.service.race;

import org.gx.chronorun.model.Race;
import org.gx.chronorun.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RaceService {

    private RaceRepository raceRepository;


    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public Race startRace(Race raceToStart) {
        raceToStart.setRaceStartDate(LocalDateTime.now());
        return raceRepository.save(raceToStart);
    }
}
