package org.gx.chronorun.api;

import org.gx.chronorun.websocket.MarkMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MarkController {

    @MessageMapping("/mark")
    @SendTo("/topic/marks")
    public MarkMessage handleMark(MarkDto mark) {
        return MarkMessage.builder().markTime(mark.getMarkTime()).build();
    }
}
