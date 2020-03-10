package org.gx.chronorun.conf;

import org.gx.chronorun.events.ContestantEventHandler;
import org.gx.chronorun.events.ResultEventHandler;
import org.gx.chronorun.events.TimeMarkEventHandler;
import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Race;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.model.TimeMark;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class RestRepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Contestant.class, Race.class, Result.class, TimeMark.class);
        config.setDefaultPageSize(Integer.MAX_VALUE);
    }

    @Bean
    public ContestantEventHandler contestantEventHandler(SimpMessagingTemplate template) {
        return new ContestantEventHandler(template);
    }

    @Bean
    public ResultEventHandler resultEventHandler(SimpMessagingTemplate template) {
        return new ResultEventHandler(template);
    }

    @Bean
    public TimeMarkEventHandler markEventHandler(SimpMessagingTemplate template) {
        return new TimeMarkEventHandler(template);
    }

}
