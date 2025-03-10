package com.plazoleta.infraestructure.input.rest.client;

import com.plazoleta.infraestructure.configuration.FeignClientConfig;
import com.plazoleta.infraestructure.input.rest.client.dto.UserAuthDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios", url = "${users-service.url}", configuration = FeignClientConfig.class)
public interface UserFeingClient {

    @GetMapping(value = "/users/{id}")
    UserAuthDto getUserById(@PathVariable("id") Long id);

    @GetMapping(value = "/users/getByEmail/{email}")
    UserAuthDto getUserByEmail(@PathVariable("email") String email);
}