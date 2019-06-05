package org.gx.chronorun.conf;

import org.gx.chronorun.model.Contestant;
import org.gx.chronorun.model.Race;
import org.gx.chronorun.model.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RestRepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Contestant.class, Race.class, Result.class);
    }
}
