package org.gx.chronorun.service.result;

import org.gx.chronorun.api.ResultDTO;
import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Race;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.repository.ContestantRepository;
import org.gx.chronorun.repository.RaceRepository;
import org.gx.chronorun.repository.ResultRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveResultTest {

    @MockBean
    private ResultRepository resultRepository;

    @MockBean
    private RaceRepository raceRepository;

    @MockBean
    private ContestantRepository contestantRepository;

    @Autowired
    private SaveResultService saveResultService;

    @Before
    public void init() {
        Mockito.when(contestantRepository.findByBib(anyInt())).
                thenReturn(Optional.of(Contestant.builder()
                        .bib(1)
                        .birthYear(1950)
                        .id("ABCDEF")
                        .category("V1M")
                        .race("raceid1")
                        .build()));
        Mockito.when(raceRepository.findById("raceid1")).thenReturn(Optional.of(Race.builder()
                .id("raceid1")
                .distance(10.2f)
                .name("Mazeres course pedestre")
                .raceDate(LocalDate.now())
                .raceStartDate(LocalDateTime.now().minus(1, ChronoUnit.HOURS))
                .build()));
    }

    @Test
    public void testSaveResult() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setBib(1);
        resultDTO.setRaceTime(LocalDateTime.now());
        ArgumentCaptor<Result> argCaptor = ArgumentCaptor.forClass(Result.class);
        saveResultService.saveResult(resultDTO);
        verify(resultRepository).save(argCaptor.capture());
        final Result result = argCaptor.getValue();
        assertEquals("raceid1", result.getRace().getId());
        assertEquals(Integer.valueOf(1), result.getContestant().getBib());
        assertEquals("ABCDEF", result.getContestant().getId());
        assertEquals(Integer.valueOf(1950), result.getContestant().getBirthYear());
    }
}
