package org.gx.chronorun.service.result;

import org.gx.chronorun.api.ResultDTO;
import org.gx.chronorun.api.ResultUpdateDto;
import org.gx.chronorun.api.TimeAdjust;
import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.repository.ResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateResultTest {

    @MockBean
    private ResultRepository resultRepository;

    @Autowired
    private UpdateResultService updateResultService;

    @Before
    public void init() {
        Mockito.when(resultRepository.findByContestantBib(1)).
                thenReturn(Optional.of(Result.builder()
                            .contestant(Contestant.builder()
                            .bib(1)
                            .birthYear(1950)
                            .id("ABCDEF")
                            .category("V1M")
                            .race("raceid1")
                            .build())
                        .raceTime(LocalDateTime.now().minus(20, ChronoUnit.MINUTES))
                        .build()));
    }

    @Test
    public void testUpdateResult() {
        ResultUpdateDto resultDTO = new ResultUpdateDto();
        resultDTO.setBib(1);
        final LocalDateTime raceTime = LocalDateTime.now();
        TimeAdjust timeadjust = new TimeAdjust();
        timeadjust.setAmount(5l);
        timeadjust.setUnit("secondes");
        timeadjust.setOperator("+");
        resultDTO.setAdjustParam(timeadjust);
        updateResultService.updateResult(resultDTO);
        ArgumentCaptor<Result> argCaptor = ArgumentCaptor.forClass(Result.class);
        verify(resultRepository).save(argCaptor.capture());
        final Result result = argCaptor.getValue();
        assertEquals(raceTime, result.getRaceTime());
    }
}
