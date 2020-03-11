package org.gx.chronorun.api;

import org.gx.chronorun.model.Race;
import org.gx.chronorun.service.race.RaceService;
import org.gx.chronorun.service.result.PrintResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/race")
public class RaceController {

    private RaceService raceService;

    private PrintResultService printResultService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PatchMapping("start")
    public ResponseEntity<Race> startRace(@RequestBody Race raceToStart) {
        return ResponseEntity.ok(raceService.startRace(raceToStart));
    }

    @GetMapping("print/{raceId}")
    public ResponseEntity<String> printResult(@PathVariable("raceId") String raceId) {
        raceService.printRaceResult(raceId);
        return ResponseEntity.ok("It's OK");
    }
}
