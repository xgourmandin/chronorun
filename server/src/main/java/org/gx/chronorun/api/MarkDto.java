package org.gx.chronorun.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class MarkDto {

    @NotNull
    private LocalDateTime markTime;

}
