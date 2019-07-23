package org.gx.chronorun.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@Builder
public class Result {

    @Id
    private String id;

    private Race race;

    private Contestant contestant;

    private LocalDateTime raceTime;

    public Duration getRaceDuration() {
        return Duration.between(race.getRaceStartDate(), raceTime);
    }

    public Float getMeanPaceByKm() {
        return getRaceDuration().toMinutes() / race.getDistance();
    }
}
