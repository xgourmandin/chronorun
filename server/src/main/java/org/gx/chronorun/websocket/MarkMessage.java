package org.gx.chronorun.websocket;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MarkMessage {

    public LocalDateTime markTime;
}
