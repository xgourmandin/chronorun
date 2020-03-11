package org.gx.chronorun.events;

import org.gx.chronorun.model.Result;
import org.gx.chronorun.websocket.ResultMessage;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;

@RepositoryEventHandler
public class ResultEventHandler extends AbstractEventHandler {

    public ResultEventHandler(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(Result result) {
        result.setRaceTime(LocalDateTime.now());
    }

    @HandleAfterDelete
    public void handleAfterDelete(Result result) {
        final ResultMessage message = buildMessage(result, "DELETE");
        send(message);
    }

    private ResultMessage buildMessage(Result result, String type) {
        return ResultMessage.builder().result(result).type(type).build();
    }

    @Override
    protected String getTopic() {
        return "/topic/result";
    }
}
