package com.plazoleta.domain.spi;

public interface IUserPersistencePort {
    Long getUserById(Long id);
    String getAuthenticatedUserId();
    Long getUserByEmail(String email);
    String getEmailByUserId(Long id);
}
