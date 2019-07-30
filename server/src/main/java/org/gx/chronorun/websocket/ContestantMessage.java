package org.gx.chronorun.websocket;

import lombok.Builder;
import lombok.Data;
import org.gx.chronorun.model.Contestant;
import org.springframework.web.socket.WebSocketHandler;

@Builder
@Data
public class ContestantMessage implements WebSocketMessage {

    private String type;

    private Contestant contestant;
}
