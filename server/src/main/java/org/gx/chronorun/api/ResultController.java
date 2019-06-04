package org.gx.chronorun.api;

import org.gx.chronorun.model.Result;
import org.gx.chronorun.service.result.SaveResultService;
import org.gx.chronorun.service.result.UpdateResultService;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/result")
public class ResultController {

    private SaveResultService saveResultService;
    private UpdateResultService updateResultService;

    public ResultController(SaveResultService createResultService, UpdateResultService updateResultService) {
        this.saveResultService = createResultService;
        this.updateResultService = updateResultService;
    }


    @PostMapping
    @SendTo("/topic/result")
    public void saveResult(ResultDTO result) {
        final Optional<Result> savedResult = saveResultService.saveResult(result);
        if(savedResult.isPresent()) {
            //TODO: Return result to Web Socket to notify every clients
        }
        else {
            //TODO: Manage errors
        }
    }

    @PatchMapping
    @SendTo("/topic/result")
    public void updateResult(ResultDTO result) {
        final Optional<Result> savedResult = updateResultService.updateResult(result);
        if(savedResult.isPresent()) {
            //TODO: Return result to Web Socket to notify every clients
        }
        else {
            //TODO: Manage errors
        }
    }

}
