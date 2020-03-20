package org.gx.chronorun.api;

import org.gx.chronorun.model.Race;
import org.gx.chronorun.service.race.RaceService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;

@RestController
@RequestMapping("api/race")
@CrossOrigin
public class RaceController {

    private RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PatchMapping("start")
    public ResponseEntity<Race> startRace(@RequestBody Race raceToStart) {
        return ResponseEntity.ok(raceService.startRace(raceToStart));
    }

    @GetMapping("print/{raceId}")
    public void resultPdf(@PathVariable("raceId") String raceId, HttpServletResponse response) throws IOException {
        response.addHeader("Content-disposition", "attachment;filename=" + raceId + ".pdf");
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        raceService.printRaceResult(raceId, response.getOutputStream());
        response.flushBuffer();
    }
}
