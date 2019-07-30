package org.gx.chronorun.events;

import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.websocket.ContestantMessage;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RepositoryEventHandler
public class ContestantEventHandler {

    private SimpMessagingTemplate messagingTemplate;

    public ContestantEventHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @HandleAfterCreate
    public void handleAfterCreate(Contestant result) {
        final ContestantMessage message = buildMessage(result, "CREATE");
        send(message);
    }

    @HandleAfterDelete
    public void handleAfterDelete(Contestant contestant) {
        final ContestantMessage message = buildMessage(contestant, "DELETE");
        send(message);
    }

    @HandleAfterSave
    public void handleAfterSave(Contestant contestant) {
        final ContestantMessage message = buildMessage(contestant, "SAVE");
        send(message);
    }

    private void send(ContestantMessage message) {
        messagingTemplate.convertAndSend("/topic/contestant", message);
    }

    private ContestantMessage buildMessage(Contestant contestant, String type) {
        return ContestantMessage.builder().contestant(contestant).type(type).build();
    }
}
