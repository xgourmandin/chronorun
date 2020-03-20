package org.gx.chronorun.service.result;

import org.gx.chronorun.api.ResultDTO;
import org.gx.chronorun.api.ResultUpdateDto;
import org.gx.chronorun.api.TimeAdjust;
import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.repository.ContestantRepository;
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

    @MockBean
    private ContestantRepository contestantRepository;

    @Autowired
    private UpdateResultService updateResultService;

    private LocalDateTime oldRaceTime;

    @Before
    public void init() {
        final Contestant contestant = Contestant.builder()
                .bib(1)
                .birthYear(1950)
                .id("ABCDEF")
                .category("V1M")
                .race("raceid1")
                .gaveUp(false)
                .build();
        oldRaceTime = LocalDateTime.now().minus(20, ChronoUnit.MINUTES);
        Mockito.when(resultRepository.findById("resid1")).
                thenReturn(Optional.of(Result.builder()
                            .contestant(contestant)
                        .raceTime(oldRaceTime)
                        .build()));
        Mockito.when(contestantRepository.findByBib(1)).thenReturn(Optional.of(contestant));
    }

    @Test
    public void testUpdateResult() {
        ResultUpdateDto resultDTO = new ResultUpdateDto();
        resultDTO.setBib(1);
        resultDTO.setResultId("resid1");
        TimeAdjust timeadjust = new TimeAdjust();
        timeadjust.setAmount(5l);
        timeadjust.setUnit("secondes");
        timeadjust.setOperator("+");
        resultDTO.setAdjustParam(timeadjust);
        updateResultService.updateResult(resultDTO);
        ArgumentCaptor<Result> argCaptor = ArgumentCaptor.forClass(Result.class);
        verify(resultRepository).save(argCaptor.capture());
        final Result result = argCaptor.getValue();
        assertEquals(oldRaceTime.plusSeconds(5), result.getRaceTime());
    }
}
