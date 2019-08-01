package org.gx.chronorun.api;

import lombok.Data;

@Data
public class ResultUpdateDto {

    private String resultId;

    private Integer bib;

    private TimeAdjust adjustParam;

}
