package org.gx.chronorun.conf;

import org.gx.chronorun.events.ContestantEventHandler;
import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Race;
import org.gx.chronorun.model.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Optional;

@Configuration
public class RestRepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Contestant.class, Race.class, Result.class);
        config.setDefaultPageSize(Integer.MAX_VALUE);
    }

    @Bean
    public ContestantEventHandler contestantEventHandler(SimpMessagingTemplate template) {
        return new ContestantEventHandler(template);
    }

}
