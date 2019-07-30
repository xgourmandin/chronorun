package org.gx.chronorun.events;

import org.gx.chronorun.websocket.WebSocketMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public abstract class AbstractEventHandler {

    private SimpMessagingTemplate messagingTemplate;

    public AbstractEventHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    protected void send(WebSocketMessage message) {
        messagingTemplate.convertAndSend(getTopic(), message);
    }

    protected abstract String getTopic();

}
