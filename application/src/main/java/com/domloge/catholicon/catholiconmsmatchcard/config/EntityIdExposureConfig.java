package com.domloge.catholicon.catholiconmsmatchcard.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.domloge.catholiconmsmatchcardlibrary.Fixture;

@Component
public class EntityIdExposureConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        
        config.exposeIdsFor(Fixture.class);
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
    }
}
