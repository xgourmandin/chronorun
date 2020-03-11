package org.gx.chronorun.api;

import org.gx.chronorun.model.Race;
import org.gx.chronorun.service.race.RaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/start")
@CrossOrigin
public class RaceController {

    private RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PutMapping
    public ResponseEntity<Race> startRace(@RequestBody  Race raceToStart) {
        return ResponseEntity.ok(raceService.startRace(raceToStart));
    }
}
