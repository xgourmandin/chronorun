package org.gx.chronorun.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ResultDTO {

    @NotNull
    private Integer bib;

    @NotNull
    private LocalDateTime raceTime;

}
