package org.gx.chronorun.api;

import org.gx.chronorun.service.contestant.ImportContestantUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@RestController
@RequestMapping("contestant")
public class ContestantController {


    ImportContestantUseCase importContestantUseCase;

    public ContestantController(ImportContestantUseCase importContestantUseCase) {
        this.importContestantUseCase = importContestantUseCase;
    }

    @PostMapping("import")
    public void uploadContestant(@RequestParam("file") MultipartFile file, @RequestParam("raceId") String raceId) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        importContestantUseCase.importContestant(reader, raceId);
    }
}
