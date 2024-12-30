package com.plazoleta.domain.usecase;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar datos de prueba
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Restaurante Prueba");
        restaurant.setAddress("Calle 123");
        restaurant.setOwnerId(1L);
        restaurant.setPhone("123456789");
        restaurant.setLogoUrl("https://example.com/logo.png");
        restaurant.setNit("901112233");
    }

    @Test
    void saveRestaurant_WhenOwnerIsValid_ShouldSaveRestaurant() {
        // Mock para validar el propietario
        when(userPersistencePort.getUserById(1L)).thenReturn(1L);

        // Ejecutar metodo
        restaurantUseCase.saveRestaurant(restaurant);

        // Verificar interacciones
        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    void saveRestaurant_WhenOwnerIsInvalid_ShouldThrowException() {
        // Mock para simular un propietario no válido
        when(userPersistencePort.getUserById(1L)).thenReturn(2L);

        // Verificar que lanza excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        assertEquals("No es un owner", exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void getRestaurant_ShouldReturnRestaurant() {
        // Mock para simular la búsqueda
        when(restaurantPersistencePort.getRestaurant(1L)).thenReturn(restaurant);

        // Ejecutar metodo
        Restaurant result = restaurantUseCase.getRestaurant(1L);

        // Validar resultado
        assertNotNull(result);
        assertEquals(restaurant.getName(), result.getName());
        verify(restaurantPersistencePort, times(1)).getRestaurant(1L);
    }

    @Test
    void getAllRestaurants_ShouldReturnListOfRestaurants() {
        // Mock para simular lista de restaurantes
        when(restaurantPersistencePort.getAllRestaurants()).thenReturn(Collections.singletonList(restaurant));

        // Ejecutar metodo
        List<Restaurant> result = restaurantUseCase.getAllRestaurants();

        // Validar resultado
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(restaurantPersistencePort, times(1)).getAllRestaurants();
    }

    @Test
    void deleteRestaurant_ShouldCallDeleteRestaurant() {
        // Ejecutar metodo
        restaurantUseCase.deleteRestaurant(1L);

        // Verificar interacción
        verify(restaurantPersistencePort, times(1)).deleteRestaurant(1L);
    }
}
