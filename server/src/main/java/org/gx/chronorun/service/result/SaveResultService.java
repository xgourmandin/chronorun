package org.gx.chronorun.service.result;

import org.gx.chronorun.api.ResultDTO;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.repository.ContestantRepository;
import org.gx.chronorun.repository.RaceRepository;
import org.gx.chronorun.repository.ResultRepository;
import org.gx.chronorun.repository.TimeMarkRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class SaveResultService {

    private final ResultRepository resultRepository;
    private final ContestantRepository contestantRepository;
    private final RaceRepository raceRepository;
    private TimeMarkRepository markRepository;

    public SaveResultService(ResultRepository resultRepository, ContestantRepository contestantRepository, RaceRepository raceRepository
            , TimeMarkRepository markRepository) {
        this.resultRepository = resultRepository;
        this.contestantRepository = contestantRepository;
        this.raceRepository = raceRepository;
        this.markRepository = markRepository;
    }

    public Optional<Result> saveResult(ResultDTO result) {
        return contestantRepository.findByBib(result.getBib()).flatMap(c ->
                raceRepository.findById(c.getRace()).map(r -> {
                    final Result finalResult = Result.builder().contestant(c).race(r).raceTime(result.getMark().getMark()).build();
                    final Result savedResult = resultRepository.save(finalResult);
                    markRepository.delete(result.getMark());
                    return savedResult;
                }));
    }

    public Optional<Result> recordGiveUp(Integer bib) {
        return contestantRepository.findByBib(bib).flatMap(c ->
                raceRepository.findById(c.getRace()).map(r -> {
                    c.setGaveUp(true);
                    final Result finalResult = Result.builder().contestant(c).race(r).build();
                    return resultRepository.save(finalResult);
                }));
    }
}