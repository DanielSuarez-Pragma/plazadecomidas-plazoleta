package com.plazoleta.domain.usecase;

import com.plazoleta.domain.exception.InvalidErrorException;
import com.plazoleta.domain.exception.NoDataFoundException;
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
import java.util.List;

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

    @Test
    void testSavePlate_Success() {
        // Configuración de mocks
        when(restaurantPersistencePort.getRestaurant(plate.getRestaurantId())).thenReturn(restaurant);
        when(userPersistencePort.getAuthenticatedUserId()).thenReturn("auth-user-id");
        when(userPersistencePort.getUserByEmail("auth-user-id")).thenReturn(restaurant.getOwnerId());

        // Llamar al metodo
        assertDoesNotThrow(() -> plateUseCase.savePlate(plate));

        // Verificar que el plato se guardó
        verify(platePersistencePort, times(1)).savePlate(plate);
    }

    @Test
    void testSavePlate_ThrowsCategoryNotFoundException() {
        // Configuración del mock para categoría inexistente
        when(categoryPersistencePort.getCategoryById(plate.getCategoryId())).thenReturn(null);

        // Verificar que lanza excepción
        assertThrows(InvalidErrorException.class, () -> plateUseCase.savePlate(plate));

        // Verificar que no se guardó nada
        verify(platePersistencePort, never()).savePlate(any(Plate.class));
    }

    @Test
    void testGetAllPlates_Success() {
        // Datos de prueba
        int page = 0, size = 5;

        // Configuración del mock
        when(platePersistencePort.getPlatesByRestaurantId(plate.getRestaurantId(), page, size))
                .thenReturn(Collections.singletonList(plate));

        // Llamar al metodo
        assertDoesNotThrow(() -> {
            List<Plate> result = plateUseCase.getAllPlates(plate.getRestaurantId(), page, size);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Pasta", result.getFirst().getName());
        });

        // Verificar la interacción con el mock
        verify(platePersistencePort, times(1))
                .getPlatesByRestaurantId(plate.getRestaurantId(), page, size);
    }

    @Test
    void testGetAllPlates_ThrowsNoDataFoundException() {
        // Datos de prueba
        int page = 0, size = 5;

        // Configuración del mock
        when(platePersistencePort.getPlatesByRestaurantId(plate.getRestaurantId(), page, size))
                .thenReturn(Collections.emptyList());

        // Verificar que lanza excepción
        assertThrows(NoDataFoundException.class,
                () -> plateUseCase.getAllPlates(plate.getRestaurantId(), page, size));

        // Verificar que el metodo del mock fue llamado
        verify(platePersistencePort, times(1))
                .getPlatesByRestaurantId(plate.getRestaurantId(), page, size);
    }

    @Test
    void testUpdatePlate_Success() {
        // Configuración del mock
        when(userPersistencePort.getAuthenticatedUserId()).thenReturn("auth-user-id");
        when(userPersistencePort.getUserByEmail("auth-user-id")).thenReturn(restaurant.getOwnerId());
        when(restaurantPersistencePort.getRestaurant(plate.getRestaurantId())).thenReturn(restaurant);
        when(platePersistencePort.getPlateById(plate.getId())).thenReturn(plate);

        // Llamar al metodo
        assertDoesNotThrow(() -> plateUseCase.updatePlate(plate));

        // Verificar que el plato fue actualizado
        verify(platePersistencePort, times(1)).updatePlate(plate);
    }

    @Test
    void testEnableDisablePlate_Success() {
        // Configuración del mock
        when(userPersistencePort.getAuthenticatedUserId()).thenReturn("auth-user-id");
        when(userPersistencePort.getUserByEmail("auth-user-id")).thenReturn(restaurant.getOwnerId());
        when(restaurantPersistencePort.getRestaurant(plate.getRestaurantId())).thenReturn(restaurant);
        when(platePersistencePort.getPlateById(plate.getId())).thenReturn(plate);

        // Cambiar estado
        plate.setActive(false);

        // Llamar al metodo
        assertDoesNotThrow(() -> plateUseCase.enableDisablePlate(plate));

        // Verificar que el estado fue actualizado
        verify(platePersistencePort, times(1)).enableDisablePlate(plate);
    }

    @Test
    void testEnableDisablePlate_ThrowsInvalidErrorException() {
        // Configuración del mock para simular que el restaurante pertenece a otro usuario
        when(userPersistencePort.getAuthenticatedUserId()).thenReturn("auth-user-id");
        when(userPersistencePort.getUserByEmail("auth-user-id")).thenReturn(2L); // ID de propietario diferente

        // Configuración del mock para devolver un restaurante válido
        when(restaurantPersistencePort.getRestaurant(plate.getRestaurantId())).thenReturn(restaurant);

        // Verificar que lanza la excepción
        assertThrows(InvalidErrorException.class, () -> plateUseCase.enableDisablePlate(plate));

        // Verificar que no se realizó ninguna operación
        verify(platePersistencePort, never()).enableDisablePlate(any(Plate.class));
    }

}