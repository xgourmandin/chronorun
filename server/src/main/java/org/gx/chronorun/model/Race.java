package org.gx.chronorun.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Race {

    @Id
    private String id;

    private String name;

    private Float distance;

    private LocalDate raceDate;

    private LocalDateTime raceStartDate;

}
