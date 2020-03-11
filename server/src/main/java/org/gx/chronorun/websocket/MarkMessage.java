package org.gx.chronorun.websocket;

import lombok.Builder;
import lombok.Data;
import org.gx.chronorun.model.TimeMark;

import java.time.LocalDateTime;

@Data
@Builder
public class MarkMessage implements WebSocketMessage{

    public String type;

    public TimeMark mark;
}
