package org.gx.chronorun.service.result;

import org.gx.chronorun.api.ResultUpdateDto;
import org.gx.chronorun.api.TimeAdjust;
import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.repository.ContestantRepository;
import org.gx.chronorun.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

@Service
public class UpdateResultService {

    private ResultRepository resultRepository;
    private ContestantRepository contestantRepository;

    public UpdateResultService(ResultRepository resultRepository, ContestantRepository contestantRepository) {
        this.resultRepository = resultRepository;
        this.contestantRepository = contestantRepository;
    }

    public Optional<Result> updateResult(ResultUpdateDto resultUpdate) {
        return contestantRepository.findByBib(resultUpdate.getBib()).map(c -> {
            Optional<Result> savedResult = resultRepository.findById(resultUpdate.getResultId()).map(r -> {
                Result updatedResult = updateBaseResult(r, resultUpdate, c);
                return resultRepository.save(updatedResult);
            });
            if (savedResult.isPresent()) {
                return savedResult.get();
            } else {
                return null;
            }
        });
    }

    private Result updateBaseResult(Result r, ResultUpdateDto resultUpdate, Contestant c) {
        r.setContestant(c);
        if (resultUpdate.getAdjustParam() != null) {
            r = adjustRaceTime(r, resultUpdate.getAdjustParam());
        }
        return r;
    }

    private Result adjustRaceTime(Result r, TimeAdjust adjustParam) {
        Long amount = adjustParam.getAmount();
        if ("-".equalsIgnoreCase(adjustParam.getOperator())) {
            amount = amount * -1;
        }

        TemporalUnit unit = ChronoUnit.SECONDS;
        if ("minutes".equalsIgnoreCase(adjustParam.getUnit())) {
            unit = ChronoUnit.MINUTES;
        }
        r.setRaceTime(r.getRaceTime().plus(amount, unit));
        return r;
    }

}
