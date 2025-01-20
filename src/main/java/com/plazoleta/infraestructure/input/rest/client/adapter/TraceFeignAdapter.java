package com.plazoleta.infraestructure.input.rest.client.adapter;

import com.plazoleta.application.dto.request.TraceRequestDto;
import com.plazoleta.domain.model.TraceClient;
import com.plazoleta.domain.model.TraceModel;
import com.plazoleta.domain.spi.ITraceFeignPersistencePort;
import com.plazoleta.infraestructure.input.rest.client.TraceFeignClient;
import com.plazoleta.infraestructure.input.rest.client.mapper.ITraceMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TraceFeignAdapter implements ITraceFeignPersistencePort {

    private final TraceFeignClient traceFeignClient;
    private final ITraceMapper traceMapper;

    @Override
    public void saveTrace(TraceModel trace) {
        TraceRequestDto traceRequestDto = traceMapper.toTraceRequest(trace);
        traceFeignClient.sendTrace(traceRequestDto);
    }

    @Override
    public List<TraceClient> findTraceClient(Long id) {
        return traceMapper.toTraceClient(traceFeignClient.getTraceByClientId(id));
    }

    @Override
    public List<TraceClient> findTraceRestaurant(Long restaurantId) {
        return traceMapper.toTraceClient(traceFeignClient.getTraceByRestaurantId(restaurantId));
    }
}
