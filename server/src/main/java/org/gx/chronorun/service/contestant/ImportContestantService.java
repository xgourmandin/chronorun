package org.gx.chronorun.service.contestant;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Sex;
import org.gx.chronorun.repository.ContestantRepository;
import org.gx.chronorun.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ImportContestantService implements ImportContestantUseCase {


    private ContestantRepository contestantRepository;
    private RaceRepository raceRepository;
    private CategoryService categoryService;

    public ImportContestantService(ContestantRepository contestantRepository, RaceRepository raceRepository, CategoryService categoryService) {
        this.contestantRepository = contestantRepository;
        this.raceRepository = raceRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void importContestant(Reader contestantReader, String raceId) {
        raceRepository.findById(raceId).ifPresent(r -> {
            log.info(String.format("Importing contestant into race %s", raceId));
            CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().withSkipHeaderRecord().withAllowMissingColumnNames();
            try (final CSVParser csvRecords = new CSVParser(contestantReader, format)) {
                List<Contestant> contestants = new ArrayList<>();
                for (CSVRecord csvRecord : csvRecords) {
                    if (!"annulée".equalsIgnoreCase(csvRecord.get(1))) {
                        final Integer birthYear = Integer.valueOf(csvRecord.get(15));
                        final Sex sex = Sex.valueOf(csvRecord.get(10));
                        String catergory = categoryService.getContestantCategory(birthYear, sex);
                        contestants.add(Contestant.builder().name(csvRecord.get(2) + " " + csvRecord.get(3)).sex(sex)
                                .bib(Integer.valueOf(csvRecord.get(11))).birthYear(birthYear)
                                .club(csvRecord.get(16)).category(catergory).race(raceId).build());
                    }
                }
                log.info(String.format("Import %d contestants into database", contestants.size()));
                contestantRepository.saveAll(contestants);
            } catch (IOException e) {
                log.error("Error reading csv file", e);
            }
        });
    }
}
