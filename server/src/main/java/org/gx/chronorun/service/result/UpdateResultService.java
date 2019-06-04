package org.gx.chronorun.service.result;

import org.gx.chronorun.api.ResultDTO;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateResultService {

    private ResultRepository resultRepository;

    public UpdateResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public Optional<Result> updateResult(ResultDTO result) {
        return resultRepository.findByContestantBib(result.getBib()).map(r -> {
            r.setRaceTime(result.getRaceTime());
            return resultRepository.save(r);
        });
    }

}
