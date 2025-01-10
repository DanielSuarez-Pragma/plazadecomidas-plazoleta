package com.plazoleta.infraestructure.configuration;

import com.plazoleta.domain.api.ICategoryServicePort;
import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.spi.ICategoryPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.usecase.CategoryUseCase;
import com.plazoleta.domain.usecase.PlateUseCase;
import com.plazoleta.domain.usecase.RestaurantUseCase;
import com.plazoleta.infraestructure.input.rest.client.UserFeingClient;
import com.plazoleta.infraestructure.out.jpa.adapter.CategoryJpaAdapter;
import com.plazoleta.infraestructure.out.jpa.adapter.PlateJpaAdapter;
import com.plazoleta.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.plazoleta.infraestructure.out.jpa.adapter.UserAdapter;
import com.plazoleta.infraestructure.out.jpa.mapper.CategoryEntityMapper;
import com.plazoleta.infraestructure.out.jpa.mapper.PlateEntityMapper;
import com.plazoleta.infraestructure.out.jpa.mapper.RestEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.ICategoryRepository;
import com.plazoleta.infraestructure.out.jpa.repository.IPlateRepository;
import com.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
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

    @Bean
    public IPlatePersistencePort platePersistencePort() {return new PlateJpaAdapter(plateRepository, plateEntityMapper);}

    @Bean
    public IPlateServicePort plateServicePort() {return new PlateUseCase(platePersistencePort(), categoryPersistencePort() , restaurantPersistencePort(), userPersistencePort());}

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);}

    @Bean
    public ICategoryServicePort categoryServicePort() {return new CategoryUseCase(categoryPersistencePort());}

}
