package org.gx.chronorun.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Contestant {

    @Id
    private String id;

    private String name;

    private Integer birthYear;

    private Integer bib;

    private Sex sex;

    private String club;

    private String category;

}
