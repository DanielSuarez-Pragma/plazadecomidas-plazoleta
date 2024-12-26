package com.plazoleta.infraestructure.input.rest.client;

import com.plazoleta.application.dto.UserDto;
import com.plazoleta.infraestructure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "usuarios", url = "${users-service.url}", configuration = FeignClientConfig.class)
public interface UserFeingClient {

    @GetMapping(value = "/users/getUsers", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getUsers();

    @GetMapping(value = "/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

}
