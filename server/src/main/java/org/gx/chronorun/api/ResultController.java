package org.gx.chronorun.api;

import org.gx.chronorun.model.Result;
import org.gx.chronorun.service.result.UpdateResultService;
import org.gx.chronorun.websocket.ResultMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/result")
@CrossOrigin
public class ResultController {

    private static final String WS_TOPIC = "/topic/result";

    private UpdateResultService updateResultService;
    private SimpMessagingTemplate messagingTemplate;

    public ResultController(UpdateResultService updateResultService, SimpMessagingTemplate messagingTemplate) {
        this.updateResultService = updateResultService;
        this.messagingTemplate = messagingTemplate;
    }

    @PatchMapping
    public Result updateResult(@RequestBody ResultUpdateDto result) {
        final Optional<Result> savedResult = updateResultService.updateResult(result);
        if(savedResult.isPresent()) {
            messagingTemplate.convertAndSend(WS_TOPIC, ResultMessage.builder().result(savedResult.get()).type("UPDATE").build());
            return savedResult.get();
        }
        throw new ResultException("Server can't update the contestant result");
    }

}
