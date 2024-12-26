package com.plazoleta.infraestructure.configuration;

import com.plazoleta.dominio.api.IRestaurantServicePort;
import com.plazoleta.dominio.spi.IRestaurantPersistencePort;
import com.plazoleta.dominio.spi.IUserPersistencePort;
import com.plazoleta.dominio.usecase.RestaurantUseCase;
import com.plazoleta.infraestructure.input.rest.client.UserFeingClient;
import com.plazoleta.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.plazoleta.infraestructure.out.jpa.adapter.UserAdapter;
import com.plazoleta.infraestructure.out.jpa.mapper.RestEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final RestEntityMapper restEntityMapper;
    private final UserFeingClient userFeingClient;


    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userFeingClient);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantService() {
        return new RestaurantUseCase(restaurantPersistencePort(), userPersistencePort());
    }
}
