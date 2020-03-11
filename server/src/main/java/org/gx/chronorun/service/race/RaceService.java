package org.gx.chronorun.service.race;

import org.gx.chronorun.model.Race;
import org.gx.chronorun.repository.RaceRepository;
import org.gx.chronorun.service.result.PrintResultService;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RaceService {

    private RaceRepository raceRepository;
    private PrintResultService printResultService;


    public RaceService(RaceRepository raceRepository, PrintResultService printResultService) {
        this.raceRepository = raceRepository;
        this.printResultService = printResultService;
    }

    public Race startRace(Race raceToStart) {
        raceToStart.setRaceStartDate(LocalDateTime.now());
        return raceRepository.save(raceToStart);
    }

    public void printRaceResult(String raceId, OutputStream out) {
        raceRepository.findById(raceId).ifPresent(r -> printResultService.computeRaceResult(r, out));
    }
}
