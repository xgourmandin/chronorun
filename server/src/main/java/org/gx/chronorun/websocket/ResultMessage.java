package org.gx.chronorun.websocket;

import lombok.Builder;
import lombok.Data;
import org.gx.chronorun.model.Result;

@Data
@Builder
public class ResultMessage {

    private String type;

    private Result result;
}
