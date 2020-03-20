package org.gx.chronorun.service.contestant;

import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Race;
import org.gx.chronorun.repository.ContestantRepository;
import org.gx.chronorun.repository.RaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContestantImportTest {

    @MockBean
    private ContestantRepository contestantRepository;

    @MockBean
    private RaceRepository raceRepository;

    @Autowired
    private ImportContestantUseCase importContestantUseCase;

    @Before
    public void init() {
        Mockito.when(raceRepository.findById("some-race")).thenReturn(Optional.of(Race.builder()
                .id("some-race")
                .distance(10.2f)
                .name("Mazeres course pedestre")
                .raceDate(LocalDate.now())
                .raceStartDate(LocalDateTime.now().minus(1, ChronoUnit.HOURS))
                .build()));
    }

    @Test
    public void testImportContestant() throws IOException {
        final Reader reader = new InputStreamReader(new ClassPathResource("csv_import.csv").getInputStream(), "UTF-8");
        try {
            importContestantUseCase.importContestant(reader, "some-race");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Mockito.verify(contestantRepository).saveAll(any());
    }

}
