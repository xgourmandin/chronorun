package org.gx.chronorun.api;

import org.gx.chronorun.model.Result;
import org.gx.chronorun.service.result.SaveResultService;
import org.gx.chronorun.websocket.ResultMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/finish")
public class FinishController {

    private static final String WS_TOPIC = "/topic/result";

    private SaveResultService saveResultService;
    private SimpMessagingTemplate messagingTemplate;

    public FinishController(SaveResultService saveResultService, SimpMessagingTemplate messagingTemplate) {
        this.saveResultService = saveResultService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    public Result saveFinishResult(@RequestBody  ResultDTO result) {
        final Optional<Result> savedResult = saveResultService.saveResult(result);
        if(savedResult.isPresent()) {
            messagingTemplate.convertAndSend(WS_TOPIC, ResultMessage.builder().result(savedResult.get()).type("CREATE").build());
            return savedResult.get();
        }
        throw new ResultException("Server can't save the contestant result");
    }

    @PostMapping("/giveup")
    public void recordGiveUp(@RequestBody Integer bib) {
        final Optional<Result> savedResult = saveResultService.recordGiveUp(bib);
        if(savedResult.isPresent()) {
            messagingTemplate.convertAndSend(WS_TOPIC, ResultMessage.builder().result(savedResult.get()).type("CREATE").build());
        }
        throw new ResultException("Server can't update the contestant result");
    }
}
