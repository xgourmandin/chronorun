package org.gx.chronorun.api;

import org.gx.chronorun.websocket.MarkMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mark")
public class MarkController {

    private SimpMessagingTemplate messagingTemplate;

    public MarkController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    public void handleMark(@RequestBody MarkDto mark) {
        messagingTemplate.convertAndSend("/topic/mark", MarkMessage.builder().markTime(mark.getMarkTime()).build());
    }
}
