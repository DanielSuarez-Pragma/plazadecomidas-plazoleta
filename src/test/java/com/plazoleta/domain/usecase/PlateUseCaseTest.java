package com.plazoleta.domain.usecase;

import com.plazoleta.domain.model.Category;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.ICategoryPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlateUseCaseTest {

    @InjectMocks
    private PlateUseCase plateUseCase;

    @Mock
    private IPlatePersistencePort platePersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;


    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    private Plate plate;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        plate = new Plate(
                1L,              // id
                "Pasta",         // name
                1L,              // categoryId
                "Delicious pasta", // description
                12.5,            // price
                1L,              // restaurantId
                "urlImage",      // imageUrl
                true              // active
        );
        restaurant = new Restaurant(
                1L,              // id
                "Italian Bistro", // name
                "123 Main St",   // address
                1L,              // ownerId
                "123-456-7890", // phone
                "logoUrl",       // logoUrl
                "123456789"      // nit
        );

        Category category = new Category(1L, "Main Dishes", "Category Description");
        when(categoryPersistencePort.getCategoryById(1L)).thenReturn(category);
    }
}