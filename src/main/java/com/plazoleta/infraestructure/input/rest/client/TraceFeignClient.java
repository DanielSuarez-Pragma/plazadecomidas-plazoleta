package com.plazoleta.infraestructure.input.rest.client;

import com.plazoleta.application.dto.request.TraceClientResponse;
import com.plazoleta.application.dto.request.TraceRequestDto;
import com.plazoleta.infraestructure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "trazabilidad", url = "${trace-service.url}", configuration = FeignClientConfig.class)
public interface TraceFeignClient {
    @PostMapping("/trazabilidad/")
    ResponseEntity<Void> sendTrace(@RequestBody TraceRequestDto traceRequestDto);

    @GetMapping("/trazabilidad/client/{id}")
    ResponseEntity<List<TraceClientResponse>> getTraceByClientId(@PathVariable("id") Long id);

    @GetMapping("/trazabilidad/{id}")
    ResponseEntity<List<TraceClientResponse>> getTraceByRestaurantId(@PathVariable("id") Long id);
}