package org.gx.chronorun.events;

import org.gx.chronorun.model.TimeMark;
import org.gx.chronorun.websocket.MarkMessage;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;

@RepositoryEventHandler
public class TimeMarkEventHandler extends AbstractEventHandler{

    public TimeMarkEventHandler(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @Override
    protected String getTopic() {
        return "/topic/mark";
    }

    @HandleBeforeCreate
    public void beforeCreate(TimeMark mark) {
        mark.setMark(LocalDateTime.now());
    }

    @HandleAfterCreate
    public void afterCreate(TimeMark mark) {
        final MarkMessage message = buildMessage(mark, "CREATE");
        send(message);
    }

    @HandleAfterDelete
    public void afterDelete(TimeMark mark) {
        final MarkMessage message = buildMessage(mark, "DELETE");
        send(message);
    }

    private MarkMessage buildMessage(TimeMark mark, String type) {
        return MarkMessage.builder().mark(mark).type(type).build();
    }
}
