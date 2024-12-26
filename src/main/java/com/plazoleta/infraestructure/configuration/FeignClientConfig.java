package com.plazoleta.infraestructure.configuration;

import feign.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class FeignClientConfig {
    @Bean
    Logger.Level feignLoggerLevel() {return Logger.Level.FULL;}
}
