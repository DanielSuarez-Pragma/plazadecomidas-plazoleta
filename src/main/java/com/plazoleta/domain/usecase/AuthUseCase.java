package com.plazoleta.domain.usecase;

import com.plazoleta.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUseCase implements UserDetailsService {

    private final IUserPersistencePort userPersistencePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserAuth userAuth = userPersistencePort.getUserByEmail(username);
        return null;
    }
}
