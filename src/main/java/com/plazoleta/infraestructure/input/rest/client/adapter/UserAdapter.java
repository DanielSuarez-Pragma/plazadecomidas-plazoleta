package com.plazoleta.infraestructure.input.rest.client.adapter;

import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infraestructure.input.rest.client.UserFeingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final UserFeingClient userFeingClient;

    @Override
    public Long getUserById(Long id) {
        return userFeingClient.getUserById(id).getRoleId();
    }

    @Override
    public String getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Suponiendo que el principal contiene el ID del usuario
            return authentication.getName();
        }
        throw new IllegalStateException("No se pudo obtener el usuario autenticado");
    }

    @Override
    public Long getUserByEmail(String email) {
        return userFeingClient.getUserByEmail(email).getId();
    }

    @Override
    public String getEmailByUserId(Long id) {
        return userFeingClient.getUserById(id).getEmail();
    }
}
