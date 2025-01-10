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

    @Test
    void savePlate() {
        // Simula la acción para un método void
        doNothing().when(platePersistencePort).savePlate(plate);

        when(restaurantPersistencePort.getRestaurant(1L)).thenReturn(restaurant);

        // Configura el mock para `userPersistencePort`
        when(userPersistencePort.getAuthenticatedUserId()).thenReturn("propietario1@plaza.com");
        when(userPersistencePort.getUserByEmail("propietario1@plaza.com")).thenReturn(1L);

        // Llama al método
        plateUseCase.savePlate(plate);

        // Verifica que el método fue llamado
        verify(platePersistencePort).savePlate(plate);
        verify(userPersistencePort).getAuthenticatedUserId();
        verify(userPersistencePort).getUserByEmail("propietario1@plaza.com");
    }



    @Test
    void getPlateById() {
        // Simula el comportamiento devolviendo un Plate
        doReturn(plate).when(platePersistencePort).getPlateById(1L);

        // Llama al método
        Plate foundPlate = plateUseCase.getPlateById(1L);

        // Verifica que el resultado sea el esperado
        assertNotNull(foundPlate);                        // Verifica que no sea null
        assertEquals(1L, foundPlate.getId());             // Compara el ID esperado

        // Verifica que el método fue invocado
        verify(platePersistencePort, times(1)).getPlateById(1L); // Verifica 1 llamada
    }



    @Test
    void getAllPlates() {
        when(platePersistencePort.getAllPlates()).thenReturn(Collections.singletonList(plate));

        assertEquals(1, plateUseCase.getAllPlates().size());
        verify(platePersistencePort, times(1)).getAllPlates();
    }

    @Test
    void updatePlate() {
        // Arrange
        Plate plate = new Plate(1L, "Pasta", 1L, "Updated description", 15.0, 1L, "urlImage", true);
        Plate existingPlate = new Plate(1L, "Old Pasta", 1L, "Old description", 10.0, 1L, "oldUrlImage", true);

        // Configuración de mocks
        when(platePersistencePort.getPlateById(1L)).thenReturn(existingPlate);
        doNothing().when(platePersistencePort).updatePlate(any(Plate.class));

        // Act
        plateUseCase.updatePlate(plate);

        // Assert
        verify(platePersistencePort).getPlateById(1L);
        verify(platePersistencePort).updatePlate(argThat(updatedPlate ->
                updatedPlate.getId().equals(plate.getId()) &&
                        updatedPlate.getName().equals(plate.getName()) &&
                        updatedPlate.getDescription().equals(plate.getDescription()) &&
                        updatedPlate.getPrice().equals(plate.getPrice())
        ));
    }

    @Test
    void enableDisablePlate() {
        // Arrange
        Plate plate = new Plate(1L, "Pasta", 1L, "Delicious pasta", 10.0, 1L, "urlImage", true); // Plato original con estado activo
        Plate updatedPlate = new Plate(1L, "Pasta", 1L, "Delicious pasta", 10.0, 1L, "urlImage", false); // Plato con el nuevo estado desactivado

        // Configuración de mocks
        when(platePersistencePort.getPlateById(plate.getId())).thenReturn(plate);
        doNothing().when(platePersistencePort).updatePlate(any(Plate.class));

        // Act
        plate.setActive(false); // Cambiar el estado activo del plato
        plateUseCase.enableDisablePlate(plate);

        // Assert
        verify(platePersistencePort).getPlateById(plate.getId());
        verify(platePersistencePort).updatePlate(argThat(updated ->
                updated.getId().equals(plate.getId()) &&
                        !updated.getActive() // Validamos que el nuevo estado sea false
        ));
    }




    @Test
    void deletePlateById() {
        doNothing().when(platePersistencePort).deletePlateById(1L);

        plateUseCase.deletePlateById(1L);

        verify(platePersistencePort, times(1)).deletePlateById(1L);
    }
}