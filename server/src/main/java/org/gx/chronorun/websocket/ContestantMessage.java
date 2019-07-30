package org.gx.chronorun.websocket;

import lombok.Builder;
import lombok.Data;
import org.gx.chronorun.model.Contestant;

@Builder
@Data
public class ContestantMessage {

    private String type;

    private Contestant contestant;
}
