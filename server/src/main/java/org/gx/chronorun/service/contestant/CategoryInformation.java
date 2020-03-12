package org.gx.chronorun.service.contestant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryInformation {

    private Integer minAge;

    private Integer maxAge;

    private String category;

}

