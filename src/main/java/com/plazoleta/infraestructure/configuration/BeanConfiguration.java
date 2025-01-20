package com.plazoleta.infraestructure.configuration;

import com.plazoleta.domain.api.*;
import com.plazoleta.domain.spi.*;
import com.plazoleta.domain.usecase.*;
import com.plazoleta.infraestructure.input.rest.client.TraceFeignClient;
import com.plazoleta.infraestructure.input.rest.client.TwilioFeignClient;
import com.plazoleta.infraestructure.input.rest.client.UserFeingClient;
import com.plazoleta.infraestructure.input.rest.client.adapter.TraceFeignAdapter;
import com.plazoleta.infraestructure.input.rest.client.adapter.TwilioFeignAdapter;
import com.plazoleta.infraestructure.input.rest.client.adapter.UserAdapter;
import com.plazoleta.infraestructure.input.rest.client.mapper.ITraceMapper;
import com.plazoleta.infraestructure.input.rest.client.mapper.ITwilioMapper;
import com.plazoleta.infraestructure.out.jpa.adapter.*;
import com.plazoleta.infraestructure.out.jpa.mapper.*;
import com.plazoleta.infraestructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final RestEntityMapper restEntityMapper;
    private final IPlateRepository plateRepository;
    private final PlateEntityMapper plateEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final IOrderPlateRepository orderPlateRepository;
    private final OrderPlateEntityMapper orderPlateEntityMapper;
    private final IRestEmpRepository restEmpRepository;
    private final RestEmpEntityMapper restEmpEntityMapper;
    private final IOrderPinRepository orderPinRepository;
    private final OrderPinEntityMapper orderPinEntityMapper;
    private final TwilioFeignClient twilioFeignClient;
    private final ITwilioMapper twilioMapper;
    private final UserFeingClient userFeingClient;
    private final TraceFeignClient traceFeignClient;
    private final ITraceMapper traceMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userFeingClient);
    }

    @Bean
    ITwilioFeignClientPort twilioFeignClientPort() {return new TwilioFeignAdapter(twilioFeignClient, twilioMapper);}

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantService() {
        return new RestaurantUseCase(restaurantPersistencePort(), userPersistencePort());
    }

    @Bean
    public IPlatePersistencePort platePersistencePort() {return new PlateJpaAdapter(plateRepository, plateEntityMapper);}

    @Bean
    public IPlateServicePort plateServicePort() {return new PlateUseCase(platePersistencePort(), categoryPersistencePort() , restaurantPersistencePort(), userPersistencePort());}

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);}

    @Bean
    public ICategoryServicePort categoryServicePort() {return new CategoryUseCase(categoryPersistencePort());}

    @Bean
    public IOrderPersistencePort orderPersistencePort() {return new OrderJpaAdapter(orderRepository, orderEntityMapper);}

    @Bean
    public IOrderServicePort orderServicePort() {return new OrderUseCase(orderPersistencePort(), restaurantPersistencePort(), userPersistencePort(), platePersistencePort(), orderPlatePersistencePort(), restEmpPersistencePort(), orderPinPersistencePort(), twilioFeignClientPort(), traceFeignPersistencePort());}

    @Bean
    public IOrderPlatePersistencePort orderPlatePersistencePort(){return new OrderPlateJpaAdapter(orderPlateRepository, orderPlateEntityMapper);}

    @Bean
    public IOrderPlateServicePort orderPlateServicePort() {return new OrderPlateUseCase(orderPlatePersistencePort());}

    @Bean
    public IRestEmpPersistencePort restEmpPersistencePort(){return new RestEmpJpaAdapter(restEmpRepository, restEmpEntityMapper);}

    @Bean
    public IRestEmpServicePort restEmpServicePort(){return new RestEmpUseCase(restEmpPersistencePort(), restaurantPersistencePort(), userPersistencePort());}

    @Bean
    public IOrderPinPersistencePort orderPinPersistencePort(){return new OrderPinJpaAdapter(orderPinRepository, orderPinEntityMapper);}

    @Bean
    public ITraceFeignPersistencePort traceFeignPersistencePort(){return new TraceFeignAdapter(traceFeignClient, traceMapper);
    }

}
