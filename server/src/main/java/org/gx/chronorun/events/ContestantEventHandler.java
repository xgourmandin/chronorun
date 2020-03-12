package org.gx.chronorun.events;

import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.service.contestant.CategoryService;
import org.gx.chronorun.websocket.ContestantMessage;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RepositoryEventHandler
public class ContestantEventHandler extends AbstractEventHandler {


    private CategoryService categoryService;

    public ContestantEventHandler(SimpMessagingTemplate messagingTemplate, CategoryService categoryService) {
        super(messagingTemplate);
        this.categoryService = categoryService;
    }

    @Override
    protected String getTopic() {
        return "/topic/contestant";
    }


    @HandleBeforeCreate
    public void handleBeforeCreate(Contestant contestant) {
        contestant.setCategory(categoryService.getContestantCategory(contestant.getBirthYear(), contestant.getSex()));
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
        final ContestantMessage message = buildMessage(contestant, "UPDATE");
        send(message);
    }

    private ContestantMessage buildMessage(Contestant contestant, String type) {
        return ContestantMessage.builder().contestant(contestant).type(type).build();
    }

}
