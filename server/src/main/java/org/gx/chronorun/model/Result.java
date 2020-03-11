package org.gx.chronorun.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Result {

    @Id
    private String id;

    private Race race;

    private Contestant contestant;

    private LocalDateTime raceTime;

    public Duration getRaceDuration() {
        if (raceTime == null) {
            return Duration.ZERO;
        }
        return Duration.between(race.getRaceStartDate(), raceTime);
    }

    public Float getMeanPaceByKm() {
        if (Duration.ZERO.equals(getRaceDuration())) {
            return 0f;
        }
        float mean = (float) Math.round((getRaceDuration().toMinutes() / race.getDistance()) * 100) / 100;
        return mean;
    }

}
