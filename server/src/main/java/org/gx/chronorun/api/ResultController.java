package org.gx.chronorun.api;

import org.gx.chronorun.model.Result;
import org.gx.chronorun.service.result.UpdateResultService;
import org.gx.chronorun.websocket.ResultMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/result")
public class ResultController {

    private static final String WS_TOPIC = "/topic/result";

    private UpdateResultService updateResultService;
    private SimpMessagingTemplate messagingTemplate;

    public ResultController(UpdateResultService updateResultService, SimpMessagingTemplate messagingTemplate) {
        this.updateResultService = updateResultService;
        this.messagingTemplate = messagingTemplate;
    }

    @PatchMapping
    public Result updateResult(ResultDTO result) {
        final Optional<Result> savedResult = updateResultService.updateResult(result);
        if(savedResult.isPresent()) {
            messagingTemplate.convertAndSend(WS_TOPIC, ResultMessage.builder().result(savedResult.get()).type("UPDATE").build());
            return savedResult.get();
        }
        throw new ResultException("Server can't update the contestant result");
    }

}
