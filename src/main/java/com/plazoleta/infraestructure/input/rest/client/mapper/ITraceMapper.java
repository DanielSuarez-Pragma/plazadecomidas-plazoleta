package com.plazoleta.infraestructure.input.rest.client.mapper;

import com.plazoleta.application.dto.request.TraceClientResponse;
import com.plazoleta.application.dto.request.TraceRequestDto;
import com.plazoleta.domain.model.TraceClient;
import com.plazoleta.domain.model.TraceModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceMapper {
    default TraceRequestDto toTraceRequest(TraceModel trace) {
        if ( trace == null ) {
            return null;
        }

        TraceRequestDto traceRequestDto = new TraceRequestDto();
        traceRequestDto.setOrderId(trace.getOrderId());
        traceRequestDto.setClientId(trace.getClientId());
        traceRequestDto.setClientEmail(trace.getClientEmail());
        traceRequestDto.setDate(trace.getDate());
        traceRequestDto.setLastState(trace.getLastState());
        traceRequestDto.setNewState(trace.getNewState());
        traceRequestDto.setEmployeeId(trace.getEmployeeId());
        traceRequestDto.setEmployeeEmail(trace.getEmployeeEmail());
        traceRequestDto.setRestaurantId(trace.getRestaurantId());
        return traceRequestDto;
    }

    default List<TraceClient> toTraceClient(ResponseEntity<List<TraceClientResponse>> traceByClientId){
        return traceByClientId.getBody().stream().map(trace ->{
            TraceClient traceClient = new TraceClient();
            traceClient.setOrderId( trace.getOrderId() );
            traceClient.setClientEmail( trace.getClientEmail() );
            traceClient.setDate( trace.getDate() );
            traceClient.setLastState( trace.getLastState() );
            traceClient.setNewState( trace.getNewState() );
            traceClient.setEmployeeEmail( trace.getEmployeeEmail() );
            return traceClient;

        }).toList();
    }
}
