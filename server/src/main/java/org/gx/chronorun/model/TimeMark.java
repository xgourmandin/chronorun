package org.gx.chronorun.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
public class TimeMark {

    @Id
    private String id;

    private LocalDateTime mark;

}
