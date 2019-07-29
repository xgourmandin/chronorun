package org.gx.chronorun.api;

import org.gx.chronorun.model.Result;
import org.gx.chronorun.service.result.SaveResultService;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/finish")
public class FinishController {

    private SaveResultService saveResultService;

    public FinishController(SaveResultService saveResultService) {
        this.saveResultService = saveResultService;
    }

    @PostMapping
    @SendTo("/topic/result")
    public Result saveFinishResult(@RequestBody  ResultDTO result) {
        final Optional<Result> savedResult = saveResultService.saveResult(result);
        if(savedResult.isPresent()) {
            //TODO: Return result to Web Socket to notify every clients
            return savedResult.get();
        }
        else {
            //TODO: Manage errors
            return null;
        }
    }
}
