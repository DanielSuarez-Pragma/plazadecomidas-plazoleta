package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.TraceClient;
import com.plazoleta.domain.model.TraceModel;

import java.util.List;

public interface ITraceFeignPersistencePort {
    void saveTrace(TraceModel trace);

    List<TraceClient> findTraceClient(Long userByEmail);

    List<TraceClient> findTraceRestaurant(Long restaurantId);
}
