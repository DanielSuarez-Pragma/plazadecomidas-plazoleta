package com.plazoleta.infraestructure.input.rest.client;

import com.plazoleta.application.dto.request.MessageRequestDto;
import com.plazoleta.infraestructure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mensajeria", url = "${send-service.url}", configuration = FeignClientConfig.class)
public interface TwilioFeignClient {
    @PostMapping("/message/")
    ResponseEntity<Void> sendMessage(@RequestBody MessageRequestDto messageRequestDto);
}
