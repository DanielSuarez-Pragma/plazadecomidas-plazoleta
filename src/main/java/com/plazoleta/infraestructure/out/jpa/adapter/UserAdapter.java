package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infraestructure.input.rest.client.UserFeingClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final UserFeingClient userFeingClient;

    @Override
    public Long getUserById(Long id) {
        return userFeingClient.getUserById(id).getRoleId();
    }

}
