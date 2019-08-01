package org.gx.chronorun.api;

import lombok.Data;

@Data
public class TimeAdjust {

    private String operator;

    private Long amount;

    private String unit;

}
