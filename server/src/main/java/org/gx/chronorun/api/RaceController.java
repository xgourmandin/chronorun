package org.gx.chronorun.api;

import org.gx.chronorun.model.Race;
import org.gx.chronorun.service.race.RaceService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/race")
public class RaceController {

    private RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PatchMapping("start")
    public Race startRace(@RequestBody  Race raceToStart) {
        return raceService.startRace(raceToStart);
    }
}
