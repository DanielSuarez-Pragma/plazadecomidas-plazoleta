package com.plazoleta.domain.usecase;

import com.plazoleta.domain.exception.InvalidErrorException;
import com.plazoleta.domain.exception.NoDataFoundException;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void testSaveRestaurant_Success() {
        // Datos de prueba
        long authenticatedOwnerId = 1L;
        long ownerRoleId = 2L; // Suponiendo que este es el ID del rol OWNER

        // Configuración del mock
        when(userPersistencePort.getAuthenticatedUserId()).thenReturn("auth-user-id");
        when(userPersistencePort.getUserByEmail("auth-user-id")).thenReturn(authenticatedOwnerId);
        when(userPersistencePort.getUserById(restaurant.getOwnerId())).thenReturn(ownerRoleId);

        // Llamar al método
        assertDoesNotThrow(() -> restaurantUseCase.saveRestaurant(restaurant));

        // Verificar que se guardó correctamente
        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    void testSaveRestaurant_ThrowsInvalidErrorException() {
        // Datos de prueba
        long authenticatedOwnerId = 2L; // Usuario autenticado diferente al propietario

        // Configuración del mock
        when(userPersistencePort.getAuthenticatedUserId()).thenReturn("auth-user-id");
        when(userPersistencePort.getUserByEmail("auth-user-id")).thenReturn(authenticatedOwnerId);
        when(userPersistencePort.getUserById(restaurant.getOwnerId())).thenReturn(3L); // Rol no válido

        // Verificar que lanza la excepción
        assertThrows(InvalidErrorException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        // Verificar que no se guardó nada
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class));
    }

    @Test
    void testGetAllRestaurants_ReturnsRestaurantList() {
        // Datos de prueba
        int page = 0;
        int size = 5;
        List<Restaurant> restaurantList = List.of(restaurant);

        // Configuración del mock
        when(restaurantPersistencePort.getAllRestaurants(page, size)).thenReturn(restaurantList);

        // Llamar al método
        List<Restaurant> result = restaurantUseCase.getAllRestaurants(page, size);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Restaurante Prueba", result.get(0).getName());

        // Verificar que el método mock fue llamado
        verify(restaurantPersistencePort, times(1)).getAllRestaurants(page, size);
    }

    @Test
    void testGetAllRestaurants_ThrowsNoDataFoundException() {
        // Datos de prueba
        int page = 0;
        int size = 5;

        // Configuración del mock
        when(restaurantPersistencePort.getAllRestaurants(page, size)).thenReturn(List.of());

        // Verificar que lanza la excepción
        assertThrows(NoDataFoundException.class, () -> restaurantUseCase.getAllRestaurants(page, size));

        // Verificar que el método mock fue llamado
        verify(restaurantPersistencePort, times(1)).getAllRestaurants(page, size);
    }
}