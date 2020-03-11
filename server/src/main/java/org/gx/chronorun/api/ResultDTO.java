package org.gx.chronorun.api;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResultDTO {

    @NotNull
    private Integer bib;

    @NotNull
    private Long raceTime;

}
